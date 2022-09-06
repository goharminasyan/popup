package com.epam.edumanagementsystem.teacher.impl;

import com.epam.edumanagementsystem.teacher.mapper.TeacherMapper;
import com.epam.edumanagementsystem.teacher.model.dto.TeacherDto;
import com.epam.edumanagementsystem.teacher.model.entity.Teacher;
import com.epam.edumanagementsystem.teacher.rest.repository.TeacherRepository;
import com.epam.edumanagementsystem.teacher.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public void create(TeacherDto teacherDto) {
        teacherRepository.save(TeacherMapper.ToTeacher(teacherDto));
    }

    @Override
    public List<TeacherDto> findAll() {
        List<Teacher> all = teacherRepository.findAll();
        List<TeacherDto> listOfTeachers = TeacherMapper.listOfTeachers(all);
        return listOfTeachers;
    }


}
