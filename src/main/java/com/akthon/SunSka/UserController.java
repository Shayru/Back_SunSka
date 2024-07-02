//package com.akthon.SunSka;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//class UserController {
//
//    private final UserRepository repository;
//
//    UserController(UserRepository repository) {
//        this.repository = repository;
//    }
//
//
//    // Aggregate root
//    // tag::get-aggregate-root[]
//    @GetMapping("/employees")
//    List<AppUser> all() {
//        return repository.findAll();
//    }
//    // end::get-aggregate-root[]
//
//    @PostMapping("/employees")
//    AppUser newEmployee(@RequestBody AppUser newEmployee) {
//        return repository.save(newEmployee);
//    }
//
//    // Single item
//
//    @GetMapping("/employees/{id}")
//    AppUser one(@PathVariable Long id) {
//
//        return repository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//    @PutMapping("/employees/{id}")
//    AppUser replaceEmployee(@RequestBody AppUser newEmployee, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setRole(newEmployee.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    return repository.save(newEmployee);
//                });
//    }
//
//    @DeleteMapping("/employees/{id}")
//    void deleteEmployee(@PathVariable Long id) {
//        repository.deleteById(id);
//    }
//}