import data.Article;
import data.User;
import repository.UserRepo;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);
        int temp;
        int login;
        int id;
        int articleId;
        boolean isPublished = false;
        String username;
        String password;
        String nationalCode;
        UserRepo userRepo = new UserRepo();
//        userRepo.creatUserTable();
//        userRepo.creatArticleTable();
        System.out.println("welcom" + "\n" + "enter 1 to log in" + "\n" + "enter 2 for register" +
                "\n" + "enter 3 for view article" + "\n" + "enter 4 for exit");
        temp =parseInt(scanner.nextLine());

        if (temp == 1) {
            System.out.println("enter your username and password");
            username = scanner.nextLine();
            password = scanner.nextLine();
            if (userRepo.logIn(username, password)) {
                System.out.println("enter 1 to view your article" + "\n" +
                        "enter 2 for edition your article" + "\n" +
                        "enter 3 for insert new article" + "\n" + "enter 4 for change your password");
                login =parseInt(scanner.nextLine());
                if (login == 1) {
                    System.out.println("enter your id");
                    id = parseInt(scanner.nextLine());
                    List<Article> myArticle = new ArrayList<>();
                    myArticle = userRepo.viewMyArticle(id);
                    for (int i=0;i<myArticle.size();i++){
                        System.out.println(myArticle.get(i));
                    }
                }
                if (login == 2) {
                    System.out.println("enter your id");
                    id = parseInt(scanner.nextLine());
                    System.out.println("enter your articleId");
                    articleId =parseInt(scanner.nextLine());
                    System.out.println("enter your article information title");
                    String title = scanner.nextLine();
                    System.out.println("brief");
                    String brief = scanner.nextLine();
                    System.out.println("content");
                    String content = scanner.nextLine();
                    System.out.println("creatDate");
                    System.out.println("year");
                    String year = scanner.nextLine();
                    System.out.println("moon");
                    String moon = scanner.nextLine();
                    System.out.println("day");
                    String day = scanner.nextLine();
                    Date creatDate= Date.valueOf(year+"-"+moon+"-"+day);
                    System.out.println("isPublished if yes enter 1 else enter 0");
                    int ispublishe = parseInt(scanner.nextLine());
                    if (ispublishe == 1)
                        isPublished= true;
                    userRepo.editMyArticle(articleId, title, brief, content, creatDate, isPublished, id);
                }
                if (login == 3) {
                    System.out.println("enter your article information to insert article id");
                    Article article = null;
                    article.setId(parseInt(scanner.nextLine()));
                    System.out.println("title");
                    article.setTitle(scanner.nextLine());
                    System.out.println("enter brief");
                    article.setBrief(scanner.nextLine());
                    System.out.println("enter content");
                    article.setContent(scanner.nextLine());
                    System.out.println("enter creat date year" );
                    String year = scanner.nextLine();
                    System.out.println("moon");
                    String moon = scanner.nextLine();
                    System.out.println("day");
                    String day = scanner.nextLine();
                    Date creatDate=Date.valueOf(year+"-"+moon+"-"+day);
                    article.setCreatDate(creatDate);
                    System.out.println("isPublished if yes enter 1 else enter 0");
                    int isPublish =parseInt(scanner.nextLine());
                    if (isPublish == 1)
                        article.setPublished(true);
                    article.setPublished(false);
                    System.out.println("enter your id");
                    article.setUserId(parseInt(scanner.nextLine()));
                    userRepo.insertArticle(article);
                }
                if (login == 4) {
                    System.out.println("enter your id");
                    id = parseInt(scanner.nextLine());
                    System.out.println("enter your new password");
                    password = scanner.nextLine();
                    userRepo.changePassword(id, password);
                }
            }else
            System.out.println("this account not find");
        }
        if (temp ==2){
            System.out.println("enter your username");
            username=scanner.nextLine();
            System.out.println("enter your nationalcode");
            nationalCode=scanner.nextLine();
            password=nationalCode;
            System.out.println("enter birthday date year");
            String year = scanner.nextLine();
            System.out.println("moon");
            String moon = scanner.nextLine();
            System.out.println("day");
            String day = scanner.nextLine();
            Date birthday=Date.valueOf(year+"-"+moon+"-"+day);

            User user=new User(1,username,nationalCode,password,birthday);
            userRepo.register(user);
        }
        if (temp ==3){
            List<Article> allArticle = new ArrayList<>();
            allArticle = userRepo.viewArticle();
            System.out.println("if you want to view complete article enter that id else enter 0");
                 id=parseInt(scanner.nextLine());
                 if(id !=0){
                     Article article= userRepo.viewCompleteArticle( id);
                 }
        }
        if (temp ==4){
        }
    }
}
