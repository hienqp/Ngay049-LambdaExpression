# LAMBDA EXPRESSION - BIỂU THỨC LAMBDA

- biểu thức Lambda hay Lambda Expression, là cách triển khai method từ 1 Functional Interface trong Java nhanh chóng mà không cần phải có các thủ tục
    - khai báo class implement interface
    - override lại method
    - khởi tạo đối tượng với toán tử new
    - gọi method từ đối tượng được khởi tạo
- Lambda Expression chỉ được sử dụng với Functional Interface

## FUNCTIONAL INTERFACE

- Functional Interface là
    - interface
    - chỉ chứa duy nhất 1 method trừu tượng

- trong Java, 1 Interface được khai báo và chỉ chứa 1 method trừu tượng thì được gọi là 1 Functional Interface
- Java cũng cung cấp 1 anotation ``@FunctionalInterface`` trước dòng khai báo interface để đánh dấu đây là Functional Interface
- ta có thể bỏ anotation này đi, nhưng ta nên đặt 1 anotation để code được rõ ràng, và nếu interface có khai báo sai với mục đích là Functional Interface thì ta sẽ nhận được thông báo lỗi ngay tại thời điểm biên dịch
- ví dụ ta khai báo 1 Functional Interface __Animal__
- __Animal.java__
```java
@FunctionalInterface
public interface Animal {
    void speak();
}
```

## KHÔNG SỬ DỤNG LAMBDA EXPRESSION

- khi không có Lambda Expression, để sử dụng Functional Interface, ta phải trải qua các bước sau
- khai báo class implement Functional Interface, ví dụ class __Cat__, và override lại các method của interface Animal
- __Cat.java__
```java
public class Cat implements Animal{
    @Override
    public void speak() {
        System.out.println("meo meo");
    }
}
```

- sau đó ta khởi tạo đối tượng của class Cat và gọi method đã override
- __Main.java__
```java
public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.speak();
    }
}
```

- khi chạy chương trình ta nhận được 
```
meo meo
```

## SỬ DỤNG BIỂU THỨC LAMBDA

### LÝ THUYẾT LAMBDA

- biểu thức Lambda (Lambda Expression) được giới thiệu trong Java 8
- biểu thức Lambda được dùng với mục đích ĐƠN GIẢN HÓA cách triển khai của Functional Interface
- thay vì phải tạo class, override method từ Functional Interface, thì với Lambda, chỉ cần khai báo cách triển khai method của Functional Interface và có thể sử dụng nó mà không phải thông quan bất kỳ class nào khác
- và với biểu thức Lambda, ta không cần cung cấp access modifier, return data, method name

### VÍ DỤ LAMBDA

- bây giờ ta sẽ tạo 1 đối tượng tên là __mouse__ có kiểu dữ liệu là __Animal__ (Animal là Functional Interface)
```
Animal mouse = 
```
- thay vì phải tạo class __Mouse__ và __implements Animal__ thì ta triển khai ngay lập tức method __speak()__ của interface __Animal__ bằng cách sử dụng cú pháp Lambda
```
() -> {};
```
- trong cặp ngoặc tròn ``()`` chứa các tham số đầu vào nếu có
- gạch nối ``-`` và dấu lớn hơn ``>`` là dấu hiệu nhận biết biểu thức Lambda
- trong cặp ngoặc nhọn và kết thúc bằng dấu chấm phẩy ``{};`` chính là nơi chứa các logic triển khai method của Functional Interface
- vì vậy khi sử dụng biểu thức Lambda ta có thể đọc hiểu như sau: khai báo 1 đối tượng có kiểu của Functional Interface với cách triển khai method như () -> {};
- nên đối tượng __mouse__ sẽ được khởi tạo và triển khai method với Lambda được viết như sau
```java
Animal mouse = () -> {
    System.out.println("chit chit");
};
```
- đối tượng đã có, cách triển khai method đã có, chỉ việc sử dụng method được triển khai thông qua đối tượng được khởi tạo
```java
Animal mouse = () -> {
    System.out.println("chit chit");
};
mouse.speak();
```

- __Main.java__
```java
public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.speak();

        Animal mouse = () -> {
            System.out.println("chit chit");
        };
        mouse.speak();
    }
}
```

- ở trên ta thấy, việc khai báo class __Cat__ là dư thừa, vì với cách sử dụng Lambda, ta không cần khai báo class và override method trong class nữa

### CÁC CÁCH VIẾT BIỂU THỨC LAMBDA TÙY TRƯỜNG HỢP

#### CÁCH TRIỂN KHAI METHOD CHỈ CÓ 1 DÒNG LỆNH

- nếu biểu thức Lambda chỉ chứa 1 dòng lệnh, ta không cần sử dụng cặp ngoặc nhọn và kết thúc bằng dấu chấm phẩy ``{};`` để chứa cách triển khai, mà có thể viết ngay dòng lệnh triển khai
```java
public class Main {
    public static void main(String[] args) {
        Animal cat = () -> System.out.println("meo meo");
        cat.speak();
    }
}
```

#### FUNCTIONAL INTERFACE CHỨA METHOD TRỪU TƯỢNG VỚI THAM SỐ

- ví dụ ta có Functional Interface __People__
```java
@FunctionalInterface
public interface People {
    void showInfo(String firstName);
}
```
- triển khai như sau
    - trong cặp ngoặc tròn khai báo tên của tham số mà không cần khai báo kiểu dữ liệu của tham số
    - ở phần triển khai nếu có sử dụng tham số thì khai báo đúng với tên tham số khai báo trong cặp ngoặc tròn
    - sau đó gọi method thông qua đối tượng được triển khai
```java
public class Main {
    public static void main(String[] args) {
//        Animal cat = () -> System.out.println("meo meo");
//        cat.speak();

        People person1 = (firstName) -> System.out.println(firstName);
        person1.showInfo("Tom");
    }
}
```
- với chỉ có 1 tham số truyền vào, nên có thể bỏ cặp ngoặc tròn
```
People person1 = firstName -> System.out.println(firstName);
```
- với method cần 2 tham số, ta phải có cặp ngoặc tròn
- interface
```java
@FunctionalInterface
public interface People {
    void showInfo(String firstName, String lastName);
}
```
- triển khai
```java
public class Main {
    public static void main(String[] args) {
//        Animal cat = () -> System.out.println("meo meo");
//        cat.speak();

        People person1 = (firstName, lastName) -> System.out.println(firstName + " " + lastName);
        person1.showInfo("Jack", "Pham");
    }
}
```

#### FUNCTIONAL INTERFACE CHỨA METHOD TRỪU TƯỢNG CÓ TRẢ VỀ DỮ LIỆU KHÁC VOID

- ví dụ interface People có method trừu tượng trả về dữ liệu là String
```java
@FunctionalInterface
public interface People {
//    void showInfo(String firstName, String lastName);
    String showInfo(String firstName, String lastName);
}
```
- thì khi triển khai, ta vẫn phải triển khai trả về với keyword ``return``, và với Lambda khi sử dụng keyword ``return`` ta phải chứa trong cú pháp ``{};``
- sau đó ta có thể thực thi method đã được triển khai, tất nhiên lúc này sẽ có dữ liệu trả về, và ta phải khai báo biến để chứa dữ liệu trả về đó
```java
public class Main {
    public static void main(String[] args) {
//        Animal cat = () -> System.out.println("meo meo");
//        cat.speak();

        People person1 = (firstName, lastName) -> {
            return firstName + " " + lastName;
        };
        String fullName = person1.showInfo("Jack", "Pham");
        System.out.println(fullName);
    }
}
```
- cuối cùng, nếu method có kiểu dữ liệu trả về và cách triển khai chỉ có 1 dòng, ta có thể không sử dụng keyword ``return`` sau đó có thể không sử dụng cú pháp ``{};``
- vì lúc này, với method có kiểu dữ liệu trả về, và triển khai với 1 dòng, Lambda sẽ hiểu đây là dòng lệnh sẽ trả về kiểu dữ liệu tương ứng
```java
public class Main {
    public static void main(String[] args) {
//        Animal cat = () -> System.out.println("meo meo");
//        cat.speak();

        People person1 = (firstName, lastName) -> firstName + " " + lastName;
        
        String fullName = person1.showInfo("Jack", "Pham");
        System.out.println(fullName);
    }
}
```