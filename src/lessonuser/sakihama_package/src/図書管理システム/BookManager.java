package lessonuser.sakihama_package.src.図書管理システム;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class BookManager {
  //フィールド
  private int nextBookId = 1;
  ArrayList<Book> bookList = new ArrayList<Book>();

  //コンストラクタ
  public BookManager() {
    try {
      File f = new File("BookList.txt");
      //ファイルが存在しない場合
      if (f.createNewFile()) {
        System.out.println("BookList.txt作成");
      }
       //ファイルの読み込み
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String name = null;
        int id = 0;
        String str = null;

        while ((str = br.readLine()) != null) {
          id = Integer.parseInt(str);
          name = br.readLine();

          //Listに追加するオブジェクトを生成
          Book book0 = new Book(name, id);
          bookList.add(book0);
        }

        //ファイルを閉じる
        br.close();
        fr.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  //GetBookListStrメソッド
  public void GetBookListStr() {
    //bookListの要素にオブジェクトが存在しない場合
    if (bookList.size() == 0) {
      System.out.println("図書は登録されていません。");
      //borrowerListの要素にオブジェクトが存在する場合
    } else {
      System.out.println("図書ID  図書名");
      for (int i = 0; i < bookList.size(); i++) {
        System.out.print(bookList.get(i).Get_id());
        System.out.print("  ");
        System.out.println(bookList.get(i).Get_name());
      }
      System.out.println();
    }
  }

  //GetBook
  public Book GetBook(int bookId) {
    //引数のIDと一致するオブジェクトが存在するか調べる
    for (int i = 0; i < bookList.size(); i++) {
      //存在する場合
      if (bookId == bookList.get(i).Get_id()) {
        return bookList.get(i);
      }
    }
    return null;
  }

  //RegisterBookメソッド
  public String RegisterBook(String bookName) {
    for (int i = 0; i < bookList.size(); i++) {
      nextBookId = bookList.get(i).Get_id() + 1;
    }

    //引数が０の場合
    if (bookName.length() == 0) {
      return "図書名が入力されていません。";
    }
    //引数が最大値を超えている場合
    if (21 <= bookName.length()) {
      return "図書名の長さが最大値を超えています。";
    }

    //引数が１字以上２０字以下の場合
    //引数を素にインスタンス生成
    Book book0 = new Book(bookName, nextBookId);
    //リストに追加
    bookList.add(book0);

    //メッセージ
    return "付与された図書ID-->" + book0.Get_id();
  }

  //RemoveBookメソッド
  public String RemoveBook(int bookId) {
    //GetBorrowerメソッドの実行結果がnullの場合
    if (GetBook(bookId) == null) {
      return "不正な図書IDが入力されました。";
    }
    //GetBorrowerメソッドを実行してオブジェクトが返ってきた場合
    //リストからGetBorrowerメソッドの戻り値（オブジェクト）を削除
    bookList.remove(GetBook(bookId));
    //メッセージ
    return "図書ID" + bookId + "を削除しました。";
  }
}