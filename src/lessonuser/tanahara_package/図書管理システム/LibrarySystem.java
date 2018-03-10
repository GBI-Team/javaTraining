package lessonuser.tanahara_package.図書管理システム;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class LibrarySystem {
  //フィールド
  LendInformationManager lim;
  BookManager bookM;
  BorrowerManager borrowerM;

  //メソッド
  //コンストラクタ
  LibrarySystem() throws Exception {
    this.lim = new LendInformationManager();
    this.bookM = new BookManager();
    this.borrowerM = new BorrowerManager();
  }

  //図書貸し出しメソッド
  public String CheckOutBook(int borrowerId, int bookId) throws Exception {
    if (borrowerM.GetBorrower(borrowerId) == null) {
      return "不正な利用者IDが入力されました。";
    }
    if (lim.GetCountOfBorrowingBooks(borrowerId) >= 3) {
      return "利用者は既に3冊の図書を借りています。";
    }
    if (bookM.GetBook(bookId) == null) {
      return "不正な図書IDが入力されました。";
    }
    if (lim.IsLentBook(bookId)) {
      return "図書は既に貸出中です。";
    }
    //返却日をString型でリターン
    return lim.AddLendInformation(borrowerM.GetBorrower(borrowerId), bookM.GetBook(bookId));
  }

  //図書返却メソッド
  public String ReturnBook(int bookId) {
    if (bookM.GetBook(bookId) == null) {
      return "不正な図書IDが入力されました。";
    }
    if (lim.GetLendInformation(bookId) == null) {
      return "図書は貸出しされていません。";
    }
    lim.RemoveLendInformation(lim.GetLendInformation(bookId));
    String str = "図書ID" + bookId + "を返却しました。";
    return str;
  }

  //利用者の登録
  public String RegisterBorrower(String borrowerName) {
    return borrowerM.RegisterBorrower(borrowerName);
  }

  //利用者の削除
  public String RemoveBorrower(int borrowerId) {
    if (lim.GetCountOfBorrowingBooks(borrowerId) != 0) {
      return "図書借用中の利用者を削除できません。";
    } else {
      return borrowerM.RemoveBorrower(borrowerId);
    }
  }

  //利用者一覧
  public void GetBorrowerListStr() {
    borrowerM.GetBorrowerListStr();
  }

  //図書の登録
  public String RegisterBook(String bookName) {
    return bookM.RegisterBook(bookName);
  }

  //図書の削除
  public String RemoveBook(int bookId) {
    if (lim.IsLentBook(bookId)) {
      return "貸出中の図書を削除できません。";
    } else {
      return bookM.RemoveBook(bookId);
    }
  }

  /**
   * 図書一覧
   */
  public void GetBookListStr() {
    bookM.GetBookListStr();
  }

  /**
   * 返却日超過者一覧
   *
   * @param date
   * @return 何を返すか
   */
  public void GetOverDateList(Date date) {
    lim.GetOverDateList(date);

  }

  //終了して保存メソッド

  /**
   * 保存
   */
  public void Save() {
    try {

      //FileWriter生成
      FileWriter fw = new FileWriter("LendInfoList.txt");

      //BufferedWriter生成
      BufferedWriter bw = new BufferedWriter(fw);

      //ストリームに書き込む
      for (int i = 0; i < lim.lendInformationList.size(); i++) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = new GregorianCalendar(new Locale("ja", "JP"));
        cal.setTime(lim.lendInformationList.get(i).Get_date());
        String redate = String.valueOf(df.format(cal.getTime()));

        bw.write(redate);
        bw.newLine();
        bw.write(Integer.toString(lim.lendInformationList.get(i).GetBorrowersID()));
        bw.newLine();
        bw.write(lim.lendInformationList.get(i).GetBorrowerName());
        bw.newLine();
        bw.write(Integer.toString(lim.lendInformationList.get(i).GetBooksID()));
        bw.newLine();
        bw.write(lim.lendInformationList.get(i).GetBookName());
        bw.newLine();
      }
      //データをファイルに書き込む
      bw.flush();

      //ストリームを閉じる
      bw.close();

      //ファイルを閉じる
      fw.close();

      //利用者ファイルに書き込み
      FileWriter fw1 = new FileWriter("BorrowerList.txt");
      BufferedWriter bw1 = new BufferedWriter(fw1);

      for (int i = 0; i < borrowerM.borrowerList.size(); i++) {
        bw1.write(Integer.toString(borrowerM.borrowerList.get(i).Get_id()));
        bw1.newLine();
        bw1.write(borrowerM.borrowerList.get(i).Get_name());
        bw1.newLine();

      }

      //データをファイルに書き込む
      bw1.flush();

      //ストリームを閉じる
      bw1.close();

      //ファイルを閉じる
      fw1.close();

      //書籍ファイルに書き込み
      FileWriter fw2 = new FileWriter("BookList.txt");

      //BufferedWriter生成
      BufferedWriter bw2 = new BufferedWriter(fw2);

      for (int i = 0; i < bookM.bookList.size(); i++) {

        bw2.write(Integer.toString(bookM.bookList.get(i).Get_id()));
        bw2.newLine();
        bw2.write(bookM.bookList.get(i).Get_name());
        bw2.newLine();

      }

      //データをファイルに書き込む
      bw2.close();

      //ファイルを閉じる
      fw2.close();

    } catch (Exception e) {
    }
  }
}