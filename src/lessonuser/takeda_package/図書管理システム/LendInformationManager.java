package lessonuser.takeda_package.図書管理システム;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class LendInformationManager {
  //フィールド
  ArrayList<LendInformation> lendInformationList = new ArrayList<LendInformation>();

  //メソッド
  //コンストラクタ
  LendInformationManager() {
    try {
      File f = new File("LendInfoList.txt");

      //ファイルが存在しない場合
      if (f.createNewFile()) {
        System.out.println("LendInfoList.txt作成");
      }
        //FileReader生成
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        Date returnDate = null;
        Book book0 = null;
        Borrower borrower0 = null;

        int lendBorrowerId = 0;
        String lendBorrowerName = null;
        int lendBookId = 0;
        String lendBookName = null;
        String str = null;

        while ((str = br.readLine()) != null) {
          returnDate = new SimpleDateFormat("yyyy/MM/dd").parse(str);
          lendBorrowerId = Integer.parseInt(br.readLine());
          lendBorrowerName = br.readLine();
          lendBookId = Integer.parseInt(br.readLine());
          lendBookName = br.readLine();

          book0 = new Book(lendBookName, lendBookId);
          borrower0 = new Borrower(lendBorrowerName, lendBorrowerId);

          lendInformationList.add(new LendInformation(returnDate, borrower0, book0));
        }

        //ストリームを閉じる
        br.close();

        //ファイルを閉じる
        fr.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  //指定されたidを持つLendInformationオブジェクトをリターンするメソッド
  public LendInformation GetLendInformation(int booksID) {
    //ArrayListのLendInformationオブジェクトのBookのidと引数が一致した場合
    for (LendInformation l : lendInformationList) {
      if ((l.GetBooksID()) == booksID) {
        return l; //LendInformaionオブジェクトをリターン
      }
    }
    //一致するオブジェクトがなかった場合
    return null; //nullをリターン
  }

  //lendInformationListにLendInformationオブジェクトを追加して返却日をリターンするメソッド
  public String AddLendInformation(Borrower borrower, Book book) throws Exception {
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    Calendar cal = new GregorianCalendar(new Locale("ja", "JP"));
    cal.setTime(new Date());
    cal.add(Calendar.DATE, 5);
    String redate = String.valueOf(df.format(cal.getTime()));
    Date returnDate = df.parse(redate);

    lendInformationList.add(new LendInformation(returnDate, borrower, book));
    return redate;
  }

  //貸出情報を削除するメソッド
  public void RemoveLendInformation(LendInformation lendInformation) {
    lendInformationList.remove(lendInformation);
  }

  //貸出判定結果をリターンするメソッド
  public boolean IsLentBook(int booksID) {
    //ArrayListのLendInformationオブジェクトのBorrowerのidと引数が一致した場合
    for (int i = 0; i < lendInformationList.size(); i++) {
      if ((lendInformationList.get(i).GetBooksID()) == booksID) {
        return true; //trueをリターン
      }
    }
    //一致するオブジェクトがなかった場合
    return false; //falseをリターン
  }

  //貸出書籍数をリターンするメソッド
  public int GetCountOfBorrowingBooks(int borrowersID) {
    int count = 0;
    for (int i = 0; i < lendInformationList.size(); i++) {
      if ((lendInformationList.get(i).GetBorrowersID()) == borrowersID) {
        count++; //LendInformationオブジェクトをリターン
      }
    }
    return count; //貸出書籍数をリターン
  }

  //返却日超過貸出者をリターンするメソッド
  public void GetOverDateList(Date date) {
    ArrayList<String> overList = new ArrayList<String>();
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
    //ArrayListのLendInformationオブジェクトのBorrowerのidと引数が一致した場合
    for (LendInformation l : lendInformationList) {
      if (l.Get_date().before(date)) {
        overList.add(l.GetBorrowerName() + ":" + l.GetBookName() + ":" + df1.format(l.Get_date()));
      }
      if (overList.size() == 0) {
        System.out.println("返却日超過貸出はありません。");
      } else {
        System.out.println("利用者名   :   図書名   :   返却日");
        for (String li : overList) {
          System.out.println(li);
        }
      }
    }
  }
}