package net.javaguides.springbootrestapi.controller;

import net.javaguides.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "ramish", "ahmed");
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok().header("custom-header", "value-custom-header").body(student);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "ramish", "ahmed"));
        students.add(new Student(2, "mouhamed", "ali"));
        students.add(new Student(3, "fatima", "ahmed"));
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{student_id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("student_id") int id, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName){
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int student_id, @RequestParam String firstName, @RequestParam String lastName){
        Student student = new Student(student_id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{student_id}/update")
    public ResponseEntity<Student> updateStudent(@PathVariable("student_id") int id, @RequestBody Student student){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{student_id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("student_id") int id){
        System.out.println(id);
        return ResponseEntity.ok("student deleted successfully!");
    }
}
