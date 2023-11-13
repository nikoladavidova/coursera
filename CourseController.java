package com.example.demo.Course;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


    @RestController
    public class CourseController {

        private static List<Course> loadAllFromFile() throws IOException {
            try (Stream<String> lines = Files.lines(Path.of("coursera_courses_fixed.csv"))) {
                return lines
                        .skip(1)
                        .map(line -> {
                            String[] parts = line.split(";");


                            return new Course(
                                    Integer.parseInt(parts[0]),
                                    parts[1],
                                    parts[2],
                                    parts[3],
                                    parts[4],
                                    Double.parseDouble(parts[5]),
                                    Integer.parseInt(parts[6]),
                                    parts[7],
                                    parts[8]

                            );
                        })
                        .toList();
            }
        }

        @GetMapping("courses")
        public Stream<Course> getAll(
/* difficulty, title, certifificateType, minRating, maxRating, pageNumber a pageSize*/
                @RequestParam(required = false) String difficulty,
                @RequestParam(required = false) String title,
                @RequestParam(required = false) String certifificateType,
                @RequestParam(required = false) Double minRating,
                @RequestParam(required = false) Double maxRating,
                @RequestParam(required = false) Integer pageSize,
                @RequestParam(required = false) Integer pageNumber
        ) throws IOException {
            long itemsToSkip = (pageNumber != null && pageSize != null) ? (pageNumber - 1) * pageSize : 0;

            return loadAllFromFile()
                    .stream()
                    .sorted(Comparator.comparingInt(Course::getId))

                    .filter(course -> difficulty==null||course.getDifficulty().toLowerCase().contains(difficulty.toLowerCase()))
                    .filter(course -> title==null||course.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .filter(course -> certifificateType==null||course.getCertificate_type().toLowerCase().contains(certifificateType.toLowerCase()))
                    .filter(course -> minRating == null || course.getRating() >= minRating )
                    .filter(course -> maxRating == null || course.getRating() <= maxRating )

                    .skip(itemsToSkip)
                    .limit(pageSize != null ? pageSize : Long.MAX_VALUE);


        }


        /*/courses/most-enrolled - vrátí kurz s nejvíce "course_students_enrolled"*/

        @GetMapping("/courses/most-enrolled")
        public Optional<Course> getMostEnrolled() throws IOException {


            return loadAllFromFile()
                    .stream()
                    .max(Comparator.comparing(Course::getStudents_enrolled));  

        }


    }
