import java.util.Scanner;

public class Runner {

    public static Scanner inp = new Scanner(System.in);

    public static void main(String[] args) {

        start();


    }

    private static void start() {

        StudentService service = new StudentService();
        service.createStudentTable();


        int id;
        int select;

        do {
            System.out.println("===================================================");
            System.out.println("Öğrenci Yönetim Paneli");
            System.out.println("1-Öğrenci Kaydetme");
            System.out.println("2-Tüm Öğrencileri Görüntüleme");
            System.out.println("3-Öğrenciyi Güncelleme");
            System.out.println("4-Öğrenciyi Silme");
            System.out.println("5-Tek Bir Öğrenciyi Görüntüleme");
            System.out.println("0-ÇIKIŞ");

            System.out.println("İşlem Seçiniz : ");
            select = inp.nextInt();
            inp.nextLine();

            switch (select) {
                case 1:
                    Student newStudent = service.getStudentinfo();
                    service.saveStudent(newStudent);
                    break;
                case 2:
                    service.printAllStudents();
                    break;
                case 3:
                    id = getIdinfo();
                    service.updateStudentById(id);
                    break;
                case 4:

                    id = getIdinfo();
                    service.deleteStudentById(id);
                    break;
                case 5:
                    id = getIdinfo();
                    service.printStudentById(id);
                    break;
                case 0:
                    System.out.println("Güle Güle");
                    break;
                default:
                    System.out.println("Hatalı giriş");
                    break;


            }


        } while (select != 0);

    }

    public static int getIdinfo() {
        System.out.println("Öğrenci id : ");
        int id = inp.nextInt();
        inp.nextLine();
        return id;

    }


}
