package lessonuser.sakihama_package.src.図書管理システム;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInterface {
  public static void main(String[] args) throws Exception {
    //インスタンス生成
    LibrarySystem ls = new LibrarySystem();

    //入力の準備
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //変換
    int select = 0;
    int borrowerID = 0;
    int bookID = 0;
    String retStr = "";
    String inStr = "";
    String pass = null;

    for (int i = 3; i >= 1; i--) {
      System.out.println("管理者パスワードを入力して下さい");
      System.out.print("password：");
      pass = br.readLine();
      if (pass.equals("p@ss")) {
        break;
      }
      if (i == 1) {
        System.out.println("パスワードが違います。");
        System.out.println("強制終了します。");
        return;
      } else {
        System.out.println("パスワードが違います。");
        System.out.println("あと" + (i - 1) + "回入力できます。");
      }
    }
    System.out.println("ようこそ、図書管理アプリケーションへ！");
    while (true) {
      try {
        System.out.println("実行する操作を選択して下さい");
        System.out.println("１：図書の貸出");
        System.out.println("２：図書の返却");
        System.out.println("３：利用者の登録");
        System.out.println("４：利用者の削除");
        System.out.println("５：利用者一覧");
        System.out.println("６：図書の登録");
        System.out.println("７：図書の削除");
        System.out.println("８：図書一覧");
        System.out.println("９：返却日超過者一覧");
        System.out.println("０：終了");
        System.out.print(">");

        select = Integer.parseInt(br.readLine());

        if (select >= 0 && select <= 9) {

          switch (select) {
          case 1: //図書の貸出
            try {
              System.out.print("利用者ID>");
              borrowerID = Integer.parseInt(br.readLine());
            } catch (NumberFormatException ne) {
              System.out.println("不正な利用者IDが入力されました。");
            }
            try {
              System.out.print("図書ID>");
              bookID = Integer.parseInt(br.readLine());
              retStr = ls.CheckOutBook(borrowerID, bookID);
              System.out.println(retStr); //実行結果を表示
            } catch (NumberFormatException ne) {
              System.out.println("不正な図書IDが入力されました。");
            }
            break;

          case 2: //図書の返却
            try {
              System.out.print("図書ID>");
              bookID = Integer.parseInt(br.readLine());
              retStr = ls.ReturnBook(bookID);
              System.out.println(retStr); //実行結果を表示
            } catch (NumberFormatException ne) {
              System.out.println("不正な図書IDが入力されました。");
            }
            break;

          case 3: //利用者の登録
            System.out.print("利用者名>");
            inStr = br.readLine();
            retStr = ls.RegisterBorrower(inStr);
            System.out.println(retStr); //実行結果を表示
            break;

          case 4: //利用者の削除
            try {
              System.out.print("利用者ID>");
              borrowerID = Integer.parseInt(br.readLine());
              retStr = ls.RemoveBorrower(borrowerID);
              System.out.println(retStr); //実行結果を表示
            } catch (NumberFormatException ne) {
              System.out.println("不正な利用者IDが入力されました。");
            }
            break;

          case 5: //利用者一覧
            ls.GetBorrowerListStr();
            break;

          case 6: //図書の登録
            System.out.print("図書名>");
            inStr = br.readLine();
            retStr = ls.RegisterBook(inStr);
            System.out.println(retStr); //実行結果を表示
            break;

          case 7: //図書の削除
            try {
              System.out.print("図書ID>");
              bookID = Integer.parseInt(br.readLine());
              retStr = ls.RemoveBook(bookID);
              System.out.println(retStr); //実行結果を表示
            } catch (NumberFormatException ne) {
              System.out.println("不正な図書IDが入力されました。");
            }
            break;

          case 8: //図書一覧
            ls.GetBookListStr();
            break;

          case 9: //返却日超過者一覧
            System.out.println("返却日(yyyy/mm/dd)>");
            String date = br.readLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date returnDate = sdf.parse(date);
            ls.GetOverDateList(returnDate);
            break;

          case 0: //終了
            ls.Save();
            return;
          }
        } else {
          System.out.println("指定された番号に対応する操作が存在しません。");
        }
      } catch (NumberFormatException ex) {
        System.out.println("０〜９の数字を入力して下さい。 ");
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
  }
}