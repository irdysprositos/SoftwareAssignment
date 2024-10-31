import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws IOException {
        System.out.println("请选择处理文件的体系结构：");
        System.out.println("EventDriven\nMasterSubprogram\nObjectOriented\nPipelineFilter");
        Scanner scanner = new Scanner(System.in);
        String architecture = scanner.next();
        System.out.println("请输入被处理文件的路径");
        String path = scanner.next();

        if(architecture.equals("EventDriven")){
            EventDriven.run();
        }else if (architecture.equals("MasterSubprogram")){
            MasterSubprogram.run();
        } else if (architecture.equals("ObjectOriented")) {
            ObjectOriented.run();
        } else if (architecture.equals("PipelineFilter")) {
            PipelineFilter.run();
        }else {
            System.out.println("不存在该结构！");
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\STUDY\\软件体系结构\\lab2output.txt"));
        String line;
        while((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
    }
}
