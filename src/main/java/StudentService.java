import java.util.List;
import java.util.Scanner;

public class StudentService {

    Scanner input = Runner.inp;

    Repository repository = new StudentRepository();

    public void createStudentTable (){

        repository.createTable();

    }

    public Student getStudentinfo(){

        System.out.println("AD :");
        String name = input.nextLine();
        System.out.println("SOYAD :");
        String lastname = input.nextLine();
        System.out.println("ŞEHİR :");
        String city = input.next();
        System.out.println("YAŞ :");
        int age = input.nextInt();
        input.nextLine();
        return new Student(name, lastname, city, age);

    }

    public void saveStudent(Student newStudent){

        repository.save(newStudent);

    }

    public void printAllStudents(){

        List<Student> students = repository.findAll();
        System.out.println("====================TÜM ÖĞRENCİLER==========================");
        for (Student student : students) {
            System.out.println("id : " + student.getId() +
                    "  ad: " + student.getName() +
                    "  soyad: " + student.getLastname() +
                    " yaş : " + student.getAge() +
                    " şehir :" + student.getCity());
        }

    }

    public Student getStudentById (int id){

        Student student = (Student) repository.findById(id);
        return student;
    }

    public void printStudentById(int id){

        Student foundStudent = getStudentById(id);

        if (foundStudent==null){

            System.out.println("Id'si verilen öprenci bulunamadı");

        }else{

            System.out.println(foundStudent);

        }

    }

    public void updateStudentById(int id){

        Student foundStudent = getStudentById(id);

        if (foundStudent==null){

            System.out.println("Öğrenci bulunamadı"+ id );

        }else {

            Student newStudent = getStudentinfo();

            foundStudent.setName(newStudent.getName());
            foundStudent.setLastname(newStudent.getLastname());
            foundStudent.setCity(newStudent.getCity());
            foundStudent.setAge(newStudent.getAge());

            repository.update(foundStudent);

        }

    }

    public void deleteStudentById(int id){

        repository.deleteById(id);

    }





}
