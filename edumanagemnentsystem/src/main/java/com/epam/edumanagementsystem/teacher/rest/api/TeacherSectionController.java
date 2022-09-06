package com.epam.edumanagementsystem.teacher.rest.api;

import com.epam.edumanagementsystem.teacher.model.dto.TeacherDto;
import com.epam.edumanagementsystem.teacher.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherSectionController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherSectionController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String openTeacherSection(Model model) {
        List<TeacherDto> all = teacherService.findAll();
        model.addAttribute("teachers", all);
        model.addAttribute("teacher", new TeacherDto());

        return "teacherSection";
    }

    @PostMapping
    public String createTeacher(@ModelAttribute("teacher") @Valid TeacherDto teacherDto,
                                BindingResult result, Model model) {
        List<TeacherDto> all = teacherService.findAll();
        model.addAttribute("teachers", all);
        if (result.hasErrors()) {
            for (TeacherDto teacher : all) {
                if (teacherDto.getEmail().equals(teacher.getEmail())) {
                    model.addAttribute("duplicated", "A user with the specified email already exists");
                }
            }
            return "teacherSection";
        } else {
            teacherService.create(teacherDto);
            return "redirect:/teacher";
        }
    }
}
