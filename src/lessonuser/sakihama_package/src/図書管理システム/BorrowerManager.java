package lessonuser.sakihama_package.src.図書管理システム;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class BorrowerManager {
  //フィールド
  private int nextBorrowerId = 1;
  ArrayList<Borrower> borrowerList = new ArrayList<Borrower>();

  //コンストラクタ
  public BorrowerManager() {
    try {
      //利用者ファイルが存在するか確認
      File f = new File("BorrowerList.txt");

      //ファイルが存在しない場合
      if (f.createNewFile()) {
        System.out.println("BorrowerList.txt作成");
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
          Borrower borrower0 = new Borrower(name, id);
          borrowerList.add(borrower0);
        }

        //ファイルを閉じる
        br.close();
        fr.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  //GetBorrowerListStrメソッド
  public void GetBorrowerListStr() {
    //borrowerListの要素にオブジェクトが存在しない場合
    if (borrowerList.size() == 0) {
      System.out.println("利用者は登録されていません。");
      //borrowerListの要素にオブジェクトが存在する場合
    } else {
      System.out.println("利用者ID  利用者名");
      for (int i = 0; i < borrowerList.size(); i++) {
        System.out.print(borrowerList.get(i).Get_id());
        System.out.print("  ");
        System.out.println(borrowerList.get(i).Get_name());
      }
      System.out.println();
    }
  }

  //GetBorrowerメソッド
  public Borrower GetBorrower(int borrowerId) {
    //引数のIDと一致するオブジェクトが存在するか調べる
    for (int i = 0; i < borrowerList.size(); i++) {
      //存在する場合
      if (borrowerId == borrowerList.get(i).Get_id()) {
        return borrowerList.get(i);
      }
    }
    return null;
  }

  //RegisterBorrowerメソッド
  public String RegisterBorrower(String borrowerName) {
    for (int i = 0; i < borrowerList.size(); i++) {
      nextBorrowerId = borrowerList.get(i).Get_id() + 1;
    }

    //引数が０の場合
    if (borrowerName.length() == 0) {
      return "利用者名が入力されていません。";
    }
    //引数が最大値を超えている場合
    if (21 <= borrowerName.length()) {
      return "利用者名の長さが最大値を超えています。";
    }

    //引数が１字以上２０字以下の場合
    //引数を素にインスタンス生成
    Borrower borrower0 = new Borrower(borrowerName, nextBorrowerId);
    //リストに追加
    borrowerList.add(borrower0);

    //メッセージ
    return "付与された利用者ID-->" + borrower0.Get_id();
  }

  //RemoveBorrowerメソッド
  public String RemoveBorrower(int borrowerId) {
    //GetBorrowerメソッドの実行結果がnullの場合
    if (GetBorrower(borrowerId) == null) {
      return "不正な利用者IDが入力されました。";
    }
    //GetBorrowerメソッドを実行してオブジェクトが返ってきた場合
    //リストからGetBorrowerメソッドの戻り値（オブジェクト）を削除
    borrowerList.remove(GetBorrower(borrowerId));
    //メッセージ
    return "利用者ID" + borrowerId + "を削除しました。";
  }
}