package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.controller.request.ApplicationFormRequest;
import com.axonactive.personalproject.entity.ApplicationForm;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.service.*;
import com.axonactive.personalproject.service.CandidateService;
import com.axonactive.personalproject.service.HiringRequestService;
import com.axonactive.personalproject.service.RecruitmentChanelService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping(path = ApplicationFormController.PATH)
@RequiredArgsConstructor
public class ApplicationFormController {
    public static final String PATH = "/api/ApplicationForms";
    @Autowired
    ApplicationFormService applicationFormService;

    @Autowired
    CandidateService candidateService;
    @Autowired
    HiringRequestService hiringRequestService;
    @Autowired
    RecruitmentChanelService recruitmentChanelService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ServletContext application;

    @GetMapping
    public ResponseEntity<List<ApplicationForm>> getAll() {
        return ResponseEntity.ok().body(applicationFormService.findAll());
    }

    @PostMapping
    public ResponseEntity<ApplicationForm> add(
            @RequestBody ApplicationFormRequest formRequest
//          , @RequestParam("file")MultipartFile file
    ) throws Exception {

      ApplicationForm newForm = applicationFormService.saveApplicationForm(
              new ApplicationForm(
                      null,
                      formRequest.getSubmittedDate(),
                      formRequest.getNoticePeriods(),
                      null,
                      formRequest.getSalaryExpectation(),
                      candidateService.findById(formRequest.getCandidateId()).get(),
                      hiringRequestService.findById(formRequest.getHiringRequestId()).get(),
                      recruitmentChanelService.findById(formRequest.getRecruitmentChanelId()).get(),
                      employeeService.findById(formRequest.getHrOfficerId()).get()
              ));


      return ResponseEntity.created(URI.create(PATH + "/" + newForm.getId())).body(newForm);
    }
        @PostMapping("/uploadImage")
        public String addImage (
                @RequestParam("file") MultipartFile file) throws Exception {
            System.out.println(file.getBytes());
            System.out.println(file.getName());
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            String Path_directory = "/Users/dino/Downloads/personalproject/src/main/resources/static/Cv";
//    String Path_directory =new ClassPathResource("static/Cv/").getFile().getAbsolutePath();
            String CvUrl = Path_directory + File.separator + file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(CvUrl), StandardCopyOption.REPLACE_EXISTING);

            return CvUrl;
        }

        @GetMapping(
                produces = MediaType.IMAGE_JPEG_VALUE
        )
        public @ResponseBody byte[] getImageWithMediaType(@RequestParam("code") String code) throws IOException {
            InputStream in = getClass()
                    .getResourceAsStream(code);
            return IOUtils.toByteArray(in);
        }


        @PutMapping("/{id}")
        public ResponseEntity<ApplicationForm> update (@PathVariable("id") Integer
        id, @RequestBody ApplicationFormRequest updatingRequest) throws ResourceNotFoundException {
            ApplicationForm updatingForm = applicationFormService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can't not find Application Form with that id."));
            updatingForm.setSubmittedDate(updatingRequest.getSubmittedDate());
            updatingForm.setNoticePeriods(updatingRequest.getNoticePeriods());
            updatingForm.setUrlCV(updatingRequest.getUrlCV());
            updatingForm.setSalaryExpectation(updatingRequest.getSalaryExpectation());
            updatingForm.setCandidate(candidateService.findById(updatingRequest.getCandidateId()).get());
            updatingForm.setHiringRequest(hiringRequestService.findById(updatingRequest.getHiringRequestId()).get());
            updatingForm.setRecruitmentChanel(recruitmentChanelService.findById(updatingRequest.getRecruitmentChanelId()).get());
            updatingForm.setHrOfficer(employeeService.findById(updatingRequest.getHrOfficerId()).get());
            ApplicationForm updatedForm = applicationFormService.saveApplicationForm(updatingForm);
            return ResponseEntity.created(URI.create(PATH + "/" + id)).body(updatedForm);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete (@PathVariable("id") Integer id) throws ResourceNotFoundException {
            ApplicationForm foundForm = applicationFormService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can't not find Application Form with that id."));
            applicationFormService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
