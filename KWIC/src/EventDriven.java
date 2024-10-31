import java.io.*;
import java.util.*;

public class EventDriven {
    interface Observer {
        void toDo();
    }

    class Subject {

        //观察者数组
        private List<Observer> vector = new LinkedList<>();


        //增加一个观察者
        public void addObserver(Observer observer) {
            vector.add(observer);
        }

        //删除一个观察者
        public void deleteObserver(Observer observer) {
            vector.remove(observer);
        }

        //通知所有观察者
        public void notifyAllObserver() {
            for(Observer observer : vector) {
                observer.toDo();
            }
        }

        // 通知特定观察者
        public void notifyOneObserver(int i) {
            vector.get(i).toDo();
        }

    }

    class KWICSubject extends Subject{
        public void startKWIC(){
            for (int i = 0;i<3;i++){
                super.notifyOneObserver(i);
            }
        }
    }

    class Input implements Observer{

        private ArrayList<String> lineTxt = new ArrayList<String>();

        public ArrayList<String> getLineTxt() {
            return lineTxt;
        }
        private String fileName;

        public Input(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void toDo() {
            BufferedReader inputFile = null;
            try {
                inputFile = new BufferedReader(new FileReader(fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String line;
            try {
                while ((line = inputFile.readLine()) != null) {
                    lineTxt.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Shift implements Observer {
        private ArrayList<String> kwicList = new ArrayList<String>();
        private ArrayList<String> lineTxt;

        public Shift( ArrayList<String> lineTxt) {
            this.lineTxt = lineTxt;
        }

        public ArrayList<String> getKwicList() {
            return kwicList;
        }


        public ArrayList<String> getLineTxt() {
            return lineTxt;
        }

        @Override
        public void toDo() {
            //获取每个单词，存入tokens
            Iterator<String> it = lineTxt.iterator();
            while (it.hasNext()) {
                StringTokenizer token = new StringTokenizer(it.next());
                ArrayList<String> tokens = new ArrayList<String>();
                int i = 0;
                //循环添加单词
                int count = token.countTokens();
                while (i < count) {
                    tokens.add(token.nextToken());
                    i++;
                }

                //display(tokens);
                //切割各个单词，不断改变起始值和利用loop实现位移。
                for (i = 0; i < count; i++) {
                    StringBuffer lineBuffer = new StringBuffer();
                    int index = i;
                    for (int f = 0; f < count; f++) {
                        //从头继续位移
                        if (index >= count)
                            index = 0;
                        //存入StringBuffer
                        lineBuffer.append(tokens.get(index));
                        lineBuffer.append(" ");
                        index++;
                    }
                    String tmp = lineBuffer.toString();
                    kwicList.add(tmp);
                }
            }

        }

    }

    public class Alphabetizer implements Observer {
        private ArrayList<String> kwicList;

        public Alphabetizer(ArrayList<String> kwicList) {
            this.kwicList = kwicList;
        }

        public ArrayList<String> getKwicList() {
            return kwicList;
        }

        @Override
        public void toDo() {
            Collections.sort(this.kwicList, new AlphabetizerComparator());
        }

        private class AlphabetizerComparator implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null && o2 == null) {
                    throw new NullPointerException();
                }
                int compareValue = 0;
                char o1c = o1.toLowerCase().charAt(0); //忽略大小写
                char o2c = o2.toLowerCase().charAt(0); //忽略大小写
                compareValue = o1c - o2c;
                return compareValue;
            }
        }

    }
    class Output implements Observer {
        private ArrayList<String> kwicList;
        private BufferedWriter outputFile;
        private String filename;
        public Output(ArrayList<String> kwicList,String filename) {
            this.kwicList = kwicList;
            this.filename = filename;
        }

        @Override
        public void toDo(){
            Iterator<String> it = kwicList.iterator();
            try {
                outputFile = new BufferedWriter(new FileWriter(filename));
                while (it.hasNext()) {
                    outputFile.write(it.next()+"\n");
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    if (outputFile!=null) {
                        outputFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void run() {
            EventDriven obj = new EventDriven();
            //创建主题
            KWICSubject kwicSubject = obj.new KWICSubject();
            //创建观察者
            Input input = obj.new Input("D:\\STUDY\\软件体系结构\\lab2input.txt");
        System.out.println(input.getLineTxt().size());
            Shift shift = obj.new Shift(input.getLineTxt());
            Alphabetizer alphabetizer = obj.new Alphabetizer(shift.getKwicList());
            Output output = obj.new Output(alphabetizer.getKwicList(), "D:\\STUDY\\软件体系结构\\lab2output.txt");

            // 将观察者加入主题
            kwicSubject.addObserver(input);
            kwicSubject.addObserver(shift);
            kwicSubject.addObserver(alphabetizer);
            kwicSubject.addObserver(output);
            // 逐步调用各个观察者
            kwicSubject.startKWIC();
        }
}
