package examware.student;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentRepo repo;

    public StudentController(StudentRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    void create(@RequestBody StudentCreationRequest creationRequest) {
        var student = new Student(creationRequest);

        repo.save(student);
    }

    @GetMapping("{name}")
    StudentDto read(@PathVariable String name) {
        return repo.findByName(name).map(Student::asDto).orElseThrow(() -> new RuntimeException("not found"));
    }

    @GetMapping("all")
    List<StudentDto> readAll() {
        return repo.findAll().stream().map(Student::asDto).toList();
    }
}
