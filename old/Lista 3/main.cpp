#include <iostream>
#include <string>
#include <cstdio>
#include <fstream>

#include "C:\Users\RTV euro AGD\Desktop\bass24\c\bass.h"
#pragma comment( lib, "C:\\Users\\RTV euro AGD\\Desktop\\bass24\\c\\bass.lib" )

using namespace std;

inline bool exists (const std::string& name) {
    if (FILE *file = fopen(name.c_str(), "r")) {
        fclose(file);
        return true;
    } else {
        return false;
    }
}
int main(int argc, char *argv[]){
    string pin;
    if ( exists("konfiguracja.txt") ){
        cout<<"Podaj pin"<< endl;
        cin>>pin;
    }
    else{//plik nie istnieje
        fstream plik;
        plik.open( "konfiguracja.txt", ios::out );
        string keystore, haslo, haslo2, alias;
        cout<<"Podaj keystore"<< endl;
        cin>>keystore;
        plik << keystore << std::endl;
        cout<<"Podaj haslo"<< endl;
        cin>>haslo;
        plik << haslo << std::endl;
        cout<<"Podaj haslo 2"<< endl;
        cin>>haslo2;
        plik << haslo2 << std::endl;
        cout<<"Podaj alias"<< endl;
        cin>>alias;
        plik << alias << std::endl;

        plik.close();
    }
    //ODTWARZACZ
    string sciezka;
    HSTREAM strumien;

		//inicjacja BASS'a
	if (!BASS_Init(-1, 44100, 0, 0, 0)){  //urzadzenie domyslne dzwieku
		BASS_Init(0, 44100, 0, 0, 0);  //blad, to bez dzwieku
		cout << "Blad";
	}
    cout << "Podaj sciezke do pliku muzycznego: ";
	getline(cin, sciezka);
		//tworzenie strumienia dzwieku
	strumien = BASS_StreamCreateFile(false, sciezka.c_str(), 0, 0, 0);
	BASS_ChannelPlay(strumien, true);  //start odtwarzania strumienia
    do{}while(BASS_ChannelIsActive(strumien));  //odtwarzanie do konca pliku
    BASS_Stop();
	BASS_Free();
    system("PAUSE");
	return EXIT_SUCCESS;
}
