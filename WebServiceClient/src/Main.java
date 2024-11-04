import com.cn.phone.GetMobileCodeInfo;
import com.cn.phone.MobileCodeWS;
import com.cn.phone.MobileCodeWSSoap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MobileCodeWS factory = new MobileCodeWS();
        MobileCodeWSSoap mobileCodeWSSoap = factory.getMobileCodeWSSoap();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter PhoneNumber:");
        String phoneNumber = input.next();
        String CodInfo = mobileCodeWSSoap.getMobileCodeInfo(phoneNumber, null);
        System.out.println(CodInfo);
    }
}