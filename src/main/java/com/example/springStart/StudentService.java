package com.example.springStart;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository repo, CourseRepository courseRepository) {
        this.repo = repo;
        this.courseRepository = courseRepository;
    }

    public StudentResponse saveStudent(StudentRequest request) {

        String cleanName = request.getName().trim();

        Optional<Student> existingStudent = repo.findByNameIgnoreCase(cleanName);

        if (existingStudent.isPresent()) {
            Student existing = existingStudent.get();
            return new StudentResponse(
                    existing.getId(),
                    existing.getName(),
                    existing.getCourse().getId(),
                    existing.getCourse().getName()
            );
        }

        //  Fetch course from DB
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        //  Attach course to student
        Student student = new Student(cleanName, course);

        Student savedStudent = repo.save(student);

        return new StudentResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                course.getId(),
                course.getName()
        );
    }

    public Page<StudentResponse> getAllStudents(Pageable pageable) {
        return repo.findAll(pageable)
                .map(student -> new StudentResponse(
                        student.getId(),
                        student.getName(),
                        student.getCourse().getId(),
                        student.getCourse().getName()
                ));
    }

    public StudentResponse getStudentById(Long id) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getCourse().getId(),
                student.getCourse().getName()
        );
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}