package examware.student;

import concept.PersistedObject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student extends PersistedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String name;
    private boolean hasPayedFee;
    private int lessonCount;

    private Student() {
    }

    public Student(StudentCreationRequest creationRequest) {
        name = creationRequest.name();
        hasPayedFee = creationRequest.hasPayedFee();
        lessonCount = creationRequest.lessonCount();
    }

    @Override
    protected Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasPayedFee() {
        return hasPayedFee;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public StudentDto asDto() {
        return new StudentDto(id, name, hasPayedFee, lessonCount);
    }
}
