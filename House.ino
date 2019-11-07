#include <ArduinoJson.h>

//
// Copyright 2015 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

// FirebaseDemo_ESP8266 is a sample that demo the different functions
// of the FirebaseArduino API.

#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "huella-95663.firebaseio.com"
#define FIREBASE_AUTH "8sQUkWIYkTJsVLS5NOCRLBwO182U3KytWmrc1b5y"
#define WIFI_SSID "PixelXL"
#define WIFI_PASSWORD "lubaspccc"
#define SALA_LED D0
#define CUARTO_LED D1
#define COSINA_LED D4
#define BANO_LED D3
#define TEMP_PIN A0

String House = "House/-LsSrCUyrUTtFNDkmnr0/";

bool Luz = true;

void setup() {

  pinMode(SALA_LED,OUTPUT);
  pinMode(CUARTO_LED,OUTPUT);
  pinMode(COSINA_LED,OUTPUT);
  pinMode(BANO_LED,OUTPUT);
  pinMode(TEMP_PIN,INPUT);

  Serial.begin(9600);
  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

}


void loop() {
  
  actulizarLuces();
  Firebase.setFloat(House+"temperatura", (analogRead(TEMP_PIN)/2));
  delay(100);
}


void actulizarLuces(){
  if(Firebase.getBool(House+"Sala")){
    digitalWrite(SALA_LED,HIGH);
  }else{
    digitalWrite(SALA_LED,LOW);
  }
  delay(50);
  if(Firebase.getBool(House+"Dormitorio")){
    digitalWrite(CUARTO_LED,HIGH);
  }else{
    digitalWrite(CUARTO_LED,LOW);
  }
  delay(50);
  if(Firebase.getBool(House+"Cosina")){
    digitalWrite(COSINA_LED,HIGH);
  }else{
    digitalWrite(COSINA_LED,LOW);
  }
  delay(50);
  if(Firebase.getBool(House+"Bano")){
    digitalWrite(BANO_LED,HIGH);
  }else{
    digitalWrite(BANO_LED,LOW);
  }
  delay(50);
}
