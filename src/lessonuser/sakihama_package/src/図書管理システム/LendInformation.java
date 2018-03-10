package lessonuser.sakihama_package.src.図書管理システム;

import java.util.Date;

public class LendInformation {
  //フィールド
  private Date returnDate; //返却日情報
  private Book book0; //Bookオブジェクト情報
  private Borrower borrower0; //Borrowerオブジェクト情報

  //コンストラクタ	受け取った引数を対応するフィールド情報に代入
  public LendInformation(Date returnDate, Borrower borrower, Book book) {
    this.returnDate = returnDate;
    this.book0 = book;
    this.borrower0 = borrower;
  }

  //メソッド
  //Bookクラスのname情報を取得して返すメソッド
  public String GetBookName() {
    return book0.Get_name();
  }

  //Borrowerクラスのname情報を取得するメソッド
  public String GetBorrowerName() {
    return borrower0.Get_name();
  }

  //BookクラスのID情報を取得するメソッド
  public int GetBooksID() {
    return book0.Get_id();
  }

  //BorrowerクラスのID情報を取得するメソッド
  public int GetBorrowersID() {
    return borrower0.Get_id();
  }

  //フィールド情報のreturnDateをリターンするメソッド
  public Date Get_date() {
    return returnDate;
  }
}