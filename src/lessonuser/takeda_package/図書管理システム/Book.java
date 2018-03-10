package lessonuser.takeda_package.図書管理システム;

public class Book {
  //フィールド
  private String name;
  private int id;

  //コンストラクタ
  public Book(String name, int id) {
    this.name = name;
    this.id = id;
  }

  //Get_nameメソッド
  public String Get_name
  () {
    return name;
  }

  //Get_idメソッド
  public int Get_id() {
    return id;
  }
}