package hellojpa;

public class ValueMain {

    public static void main(String[] args) {
        /**
         * 값 타입 테스트
         */
        // // intA와 intB는 다른 저장 공간을 가지는 아예 다른 값!!! (공유가 안됨)
        // int intA = 10;
        // int intB = intA;
        //
        // intA = 20;
        //
        // System.out.println("intA = " + intA); // 20
        // System.out.println("intB = " + intB); // 10
        //
        // // integerA와 integerB는 값이 공유가 됨
        // Integer integerA = new Integer(10);
        // Integer integerB = integerA; // integerA의 주소 값만 넘어감
        //
        // integerA = 10;
        //
        // System.out.println("integerA = " + integerA); // 10
        // System.out.println("integerB = " + integerB); // 10

        /**
         * equals 재정의
         */
        int a = 10;
        int b = 10;

        System.out.println("(a==b) = " + (a == b)); // true

        Address address1 = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");

        System.out.println("(address1 == address2) = " + (address1 == address2)); // false
        System.out.println("(address1.equals(address2))" + (address1.equals(address2))); // true
    }
}
