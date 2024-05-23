#include<iostream>
#include<fstream>

int CharCount(std::fstream& file){
    int cnt = 0;
    char temp;
    while(file.get(temp)){
        cnt ++;
    }
    return cnt;
}

int WordCount(std::fstream& file){
    int cnt = 0;
    char temp;
    while(file.get(temp)){
        if(temp == ' ' ||
        temp == '.' ||
        temp == ','||
        temp == '!')
        cnt ++;
    }
    return cnt;
}
int main(int argc, char* argv[]){
    std::string path;
    path = argv[2];

    std::fstream file(path, std::ios::in);
    int cnt;
    std::string op = argv[1];
    if(op == "-c"){
        cnt = CharCount(file);
        std::cout << "numbers of char: " << cnt;
    }else{
        cnt = WordCount(file);
        std::cout << "numbers of word: " << cnt;
    }
    file.close();

}