package appbuilder.backapp.controller;

import appbuilder.backapp.adapter.mapper.AppMapper;
import appbuilder.backapp.adapter.mapper.FormMapper;
import appbuilder.backapp.core.data.AppDTO;
import appbuilder.backapp.core.data.FormDTO;
import appbuilder.backapp.core.entity.App;
import appbuilder.backapp.core.usecase.CreateAppUseCase;
import appbuilder.backapp.core.usecase.FindAppUseCase;
import appbuilder.backapp.core.usecase.FindFormUseCase;
import appbuilder.backapp.core.usecase.ModifyAppUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/application")
@CrossOrigin(origins = "http://localhost:4200")
public class AppController {
    @Autowired
    private FindFormUseCase findFormUseCase;
    @Autowired
    private FindAppUseCase findAppUseCase;
    @Autowired
    private CreateAppUseCase createAppUseCase;
    @Autowired
    private ModifyAppUseCase modifyAppUseCase;


    @PutMapping("/change-status")
    public ResponseEntity<AppDTO> changeStatus(@RequestBody AppDTO appDto) {
        modifyAppUseCase.changeStatus(appDto);

        return new ResponseEntity<>(appDto,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AppDTO> update(@RequestBody AppDTO appDto) {
        modifyAppUseCase.update(appDto);

        return new ResponseEntity<>(appDto,HttpStatus.OK);
    }

    @PostMapping("/new-app")
    public ResponseEntity<AppDTO> createApp(@RequestBody AppDTO appDto) {
        App app = new AppMapper().deepCopy(appDto);

        createAppUseCase.insertApp(app);

        return new ResponseEntity<>(appDto,HttpStatus.CREATED);
    }

    @PostMapping("/create-app-form")
    public ResponseEntity<AppDTO> createAppForm(@RequestBody AppDTO appDto) {
        App app = new AppMapper().deepCopy(appDto);

        createAppUseCase.persistAppForm(app);

        return new ResponseEntity<>(appDto,HttpStatus.CREATED);
    }

    @GetMapping("/forms")
    public List<FormDTO> getForms(
                @RequestParam("appId")
                String appId
            ) {
        return findFormUseCase.findAll(Long.valueOf(appId)).stream()
                              .map(form -> new FormMapper().toDto(form))
                              .collect(Collectors.toList());
    }

    @GetMapping("/app/{id}")
    public AppDTO getApp(@PathVariable("id")String appId) {
        App app = findAppUseCase.findById(Long.valueOf(appId));
        AppDTO dto = new AppMapper().toDto(app);
        dto.setForms(
            app.getForms().stream()
               .map(form -> new FormMapper().toDto(form))
               .collect(Collectors.toList())            
        );
        return dto;
    }

    @GetMapping("/all-with-any-status")
    public List<AppDTO> getAllWithAnyStatus() {
        return findAppUseCase.findAllWithAnyStatus().stream()
                             .map(app -> new AppMapper().toDto(app))
                             .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<AppDTO> getAll() {
        return findAppUseCase.findAll().stream()
                             .map(app -> new AppMapper().toDto(app))
                             .collect(Collectors.toList());
    }


}
