package com.example.springStart;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public StudentResponse saveStudent(StudentRequest request) {

        String cleanName = request.getName().trim();

        Optional<Student> existingStudent =
                repo.findByNameIgnoreCase(cleanName);

        if (existingStudent.isPresent()) {
            Student existing = existingStudent.get();
            return new StudentResponse(existing.getId(), existing.getName());
        }

        Student student = new Student(cleanName);
        Student savedStudent = repo.save(student);

        return new StudentResponse(savedStudent.getId(), savedStudent.getName());
    }


    public Page<StudentResponse> getAllStudents(Pageable pageable) {
        return repo.findAll(pageable)
                .map(student -> new StudentResponse(
                        student.getId(),
                        student.getName()
                ));
    }

    public StudentResponse getStudentById(Long id) {
        Student student = repo.findById(id).orElse(null);

        if (student == null) {
            return null;
        }

        return new StudentResponse(student.getId(), student.getName());
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}