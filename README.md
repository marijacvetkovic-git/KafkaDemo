# Upotreba Apche Kafke i primena pub/sub mehanizma
## Apche Kafka
### Šta je to Apache Kafka i kako je nastala?

Apache Kafka je open-source distribuirana platforma za strimovanje event-a (događaja, poruka) i omogućava njihovo skladištenje i brz prenos velikih količina tih podataka u realnom vremenu . 
Obezbeđuje nisku latenciju( podaci prenose ili obrađuju vrlo brzo, gotovo trenutno),
visoki protok podataka( sistem može da rukuje ogromnim količinama informacija istovremeno, bez usporavanja ili gubitaka) ,skalabinost i pouzdanost. Zbog ovoih karakterstika je pogodno koristi kafku u sistemima za analitiku, integraciju podataka i event-driven sistemima.

Kafka je nastala 2010.godine u okviru kompanije Linkedln kao rešenje na problem nepostojanja dovoljno dobrog sistema za razmenu poruka. Tadašnji sistemi nisu bili dovoljno brzi, skalabilni i nisu mogli da podrže veliki protok podataka.

Ti sistemi su bili spori, imali su ograničen protok podataka i jednostavno nisu bili dizajnirani da obrađuju tokove podataka u realnom vremenu.
Ovakvi sistemi, poput ActiveMQ ili RabbitMQ, nisu dizajnirani da se nose sa ovolikom količinom podataka i zahtevima za brzom obradom. Oni obično imaju:

1. **Ograničen kapacitet:**
 Ne mogu da obrade veliki broj poruka istovremeno.
2. **Veću latenciju:**
 Obrada i slanje poruka traje duže, što je neprihvatljivo za aplikacije u realnom vremenu.
3. **Teško skaliranja:**
   Ovi sistemi teško mogu da povećaju kapacitet kako raste broj korisnika i količina podataka.
   
 Inženjerski tim LinkedIn-a rešio je ova ograničenja kreiranjem novog sistema za razmenu poruka koji može da rukuje velikim količinama tokova podataka u realnom vremenu da se skalira horizontalno. To rešenej je dobilo ime po piscu Franc-u Kafki.
Sa porastom popularnosti drustevnih mreza ovi problemi su postajali sve veći probelm tako da su koosnivaci resili da napre sistem koji ce ispuniti njihove zahteve.

### Prednosti
Prednosti Kafke su:
1. **Visoka propusnost:**
<br />Kafka omogućava isporuku poruka pri maksimalnoj brzini mreže sa latencijama manjim od 2 ms, koristeći klaster servera.

2. **Skalabilnost (Scalable):**
<br /> Klasteri se mogu skalirati na hiljade brokera, trilione poruka dnevno, petobajte podataka i stotine hiljada particija. 

3. **Trajno skladištenje (Permanent Storage):**
<br />Bezbedno skladišti tokove podataka u distribuiranom, trajnom i otpornom (fault-tolerant) klasteru.

4. **Visoka dostupnost (High Availability):**
<br /> Visoka dostupnost se postiže kroz mehanizme replikacije, particioniranja i failover-a. 
5. **Kompleksnost migracija i integracija:**
<br />Migracija između različitih sistema može biti složena zbog neusklađenih protokola ili formata podataka. Kafka funkcioniše kao posrednik za podatke između starog i novog sistema.
Stari sistem šalje podatke u Kafka temu. Novi sistem može preuzeti podatke iz te teme kada je spreman. Kafkina fleksibilnost omogućava integraciju sa alatima za transformaciju podataka (npr. Kafka Connect, Debezium, ili prilagođeni procesori) kako bi se format podataka prilagodio potrebama novog sistema.Ako novi sistem naiđe na problem, Kafka omogućava ponovni pregled (replay) i procesiranje podataka bez potrebe za ponovnim slanjem iz izvornog sistema.

### Upotreba Apche Kafke
1. **Razmena poruka:**
  <br /> Kafka je dobra zamena za message brokere kao sto je RabbitMq. Message brokeri razdvajaju obrade podataka od proizvođača podataka (decoupling), cuvaju neobradjene poruke(neki i obradjene). Za razliku od obicnih message brokera kafka nudi veći protok podataka i ima ugrađeno particionisanje, replikaciju i otpornost na greške.
2. **Praćenje aktivnosti korisnika:**
<br />Originalni slučaj upotrebe za Kafku(u Linkeldin-inu) bio je rekonstrukcija sistema za praćenje aktivnosti korisnika kao niza real-time pub-sub tokova. Čitanjem ovih tokova podataka moguce je raditi real-time obradu, real-time monitoring (sistemi prate dolazne podatke u trenutku njihovog generisanja kako bi otkrili nepravilnosti, analizirali performanse ili aktivirali alarme, npr banka može koristiti real-time monitoring za otkrivanje prevara na osnovu transakcija korisnika.Podaci iz tema se prenose u alate za nadzor, kao što su Grafana ili Kibana, radi vizualizacije i praćenja.
) i ucitavanje podataka u Hadoop za kasniju obradu.

3. **Agregacija logova:**
<br />Agregacija logova je proces prikupljanja i objedinjavanja log fajlova koji se generišu na različitim serverima ili aplikacijama, i njihovo prebacivanje na centralnu lokaciju radi analize, praćenja i obrade.Obicno log fajlovi se ručno ili putem specijalnih alata skupljaju i prebacuju u centralizovano skladište, poput file servera, baze podataka ili Hadoop Distributed File System (HDFS) i onda analitičari ili sistemi obrađuju te podatke kako bi izvukli korisne informacije, kao što su identifikacija grešaka, praćenje performansi ili bezbednosna analiza.
Umesto da radi sa fizičkim fajlovima, Kafka tretira svaki log kao tok poruka, što olakšava obradu i analizu podataka. Kafka može istovremeno prikupljati logove iz više izvora (npr. web servera, aplikacija, IoT uređaja) i objedinjavati ih na jednom mestu.

4. **Obrada tokova(stream-ova podataka):**
<br />Podaci se agregiraju, obogaćuju ili transformišu u nove teme za dalju obradu.Kafka Streams je biblioteka koja omogućava ovu vrstu obrade u realnom vremenu. Alternativni alati uključuju Apache Storm i Apache Samza.

Takođe služi kao spoljasni commit-log,event sourcing i jos mnogo toga.

### Mane
1. **Kompleksna imeplementacija i upravljanje**
2. **Kafka zahteva značajnu računarsku i mrežnu snagu, posebno za veće sisteme sa velikim brojem poruka.Potrebno je ulaganje u infrastrukturu kako bi Kafka mogla da pravilno funkcioniše, a resursi (CPU, memorija, diskovni prostor) moraju biti pažljivo planirani, kako bi se obezbedio optimalan rad.**
3. **Potrebno je imati dobre alate za monitoring i administraciju Kafke, <br /> jer bez odgovarajuće kontrole i praćenja rada Kafke, mogu nastati problemi poput gubitka podataka, zastoja u obradama i neefikasnog skaliranja.**
4. **Kafka je bolje prilagođena za obradu podataka u stvarnom vremenu, a nije najbolja opcija za sisteme koji se oslanjaju na batch processing. Ako vaša aplikacija zahteva obradu podataka u serijama ili ne zahteva nisku latenciju, rešenja kao što su Apache Spark ili Hadoop mogu biti pogodnija.**

Na osnovu mana dolazimo do sledećeg zaključka.

### Kafku ne treba koristiti kada : 
Kafka je bolje prilagođena za obradu podataka u stvarnom vremenu, a nije najbolja opcija za sisteme koji se oslanjaju na batch processing.

Ako aplikacija zahteva obradu podataka u serijama ili ne zahteva nisku latenciju, rešenja kao što su Apache Spark ili Hadoop mogu biti pogodnija.

### Kompanije koje koriste Apache Kafku
Prema Kafiknom sajtu više od 80% svih kompanija sa Fortune 100 liste koristi Kafku. Fortune 100 lista je lista koju svake godine objavljuje Fortune magazin i ta lista rangira najveće kompanije u Sjedinjenim Američkim Državama na osnovu njihovog ukupnog prihoda.To su uglavnom globalne korporacije sa velikim prihodima i uticajem na svetsko tržište.

Neke od kompanije koje koriste Apche Kafku su
1. **Linkedln**
<br />Svaki korisnički događaj (npr. lajkovanje posta, slanje poruke, pregled profila) kreira "događaj" koji Kafka obrađuje i prosleđuje odgovarajućim sistemima.Može vrsiti obradu nekih podataka..npr postoji bilioteka Kafka streams koja može da radi filtiranje, mapiranje tih podataka..npr da ne yelimo da se obrajduju duplikati ili poruke starije do nekog timestamp-a.
2. **Uber**
<br />Pracenje voznje i cuvanje podataka za kasniju analizu
3. **Netflix**
<br />Obrada događaja u realnom vremenu, poput korisničke aktivnosti (npr. gledanje filmova, preporuke).Koristi se i za integraciu različitih mikroservisa.
4. **Spotify**
<br />Analiza u realnom vremenu za kreiranje personalizovanih plejlisti.
5. **Microsoft**
<br />Upravljanje velikim volumenom podataka u uslugama poput Azure Event Hubs, koristi se za real-time analitika i integracija sa oblakom.

## Publisher/Subscriber model
<br />Model "publish/subscribe" (često skraćeno pub/sub) je arhitekturni obrazac u računarstvu koji se koristi za razmenu poruka između komponenti sistema na efikasan i fleksibilan način.
Osnovna ideja je razdvajanje publisher-a i pretplatnika (subscribers) tako da oni ne komuniciraju direktno, već preko posrednika (message broker-a). Ovo omogućava skalabilnost, fleksibilnost i lakše održavanje sistema. Koristi u razvoju softvera, naročito u distribuiranim sistemima i arhitekturama mikroservisa.

Pub/Sub se sastoji od tri osnovna entiteta:

**Publisher**- salje poruke
<br />-> Ne mora da zna ko će primiti poruke.
<br />-> Njegov zadatak je da pošalje poruku na određenu temu (topic).
<br />-> Publisher šalje poruku brokeru i naglasava temu (topic) na koju poruka treba da se pošalje.

**Subscriber** - prima poruke
<br />-> Pretplaćuje se na određene teme (topics) koje su od interesa.
<br />-> Prima samo one poruke koje odgovaraju temama na koje je pretplaćen.
<br />
<br />
**Broker (posrednik)**- upravlja isporukom poruka između publisher-a i subscriber-a
<br />-> Osigurava da poruke koje publusher šalje na topik budu isporučene svim subscriber-ima koji slusaju taj topik.
<br />-> Broker prima poruku i proverava koji su pretplatnici zainteresovani za tu temu.Zatim šalje poruku svim relevantnim pretplatnicima.

## Arhitektura Apache Kafke i njeno korišćenje pub/sub modela
Osnovna arhitektura Kafke obuhvata:
1. **Record(event,poruka)**
2. **Producer**
3. **Consumer**
4. **Topic**
5. **Partiticion (Particije)**
6. **Broker**
7. **Consumer group**
8. **ZooKeeper**

![image](https://github.com/user-attachments/assets/cb1f2e53-fdcf-4d61-ba7a-d38bb3b3d045)


**Rekordi (poruke, događaji)**
Kada se desi neki dogadjaj (npr neki korisnik je kliknuo na odredjeno dugme , neko je poslao novac na racun) ti dogadjaji ili takozvani rekordi(poruke) imaju određenu strukturu. Imaju ključ,vrednost,timestamp i opcione metapodatke. Poruke se šalju na određene topike. Topik je zapravo mesto gde su poruke čuvaju. On se  može posmatarti kao folder, a poruke kao fajlovi u filesystem-u. <br />Poruke se čuvaju na disku. Može se nagalsiti koliko dugo se poruke čuvaju.
Kafka topici mogu imati 0,1 ili više producer-a koji upisuju poruke u njih, a takođe 0,1 ili više consumer-a koji mogu čitati iz tih topika. Poruke(dogadji ili rekordi) se sa Kafka topika mogu pročitati kad god su neophodne jer Kafka omogućuje čuvanje poruka na zadati vremenski period. Može da se definiše koliko dugo Kafka treba da zadrži poruke pomoću podešavanja na nivou topika (per-topic configuration setting), nakon čega će stari događaji biti odbačeni.

**Producer i consumer** funkcionišu na isti način kao u bilo kojoj pub/sub arhitekturi.

**Broker**
Rekli smo da je kafka distriburana event-streming platforma. Svaki server-servis koji se pokreće u kafka klasteru se naziva broker. 

**Particije i topici**
Topici su podaljeni u particije. Broj particija se odredjuje pri kreiranju topika. Particije jednog topika se mogu nalaziti na različitim brokerima. Ovakva raspodela omogućava sistemu da istovremeno koristi više servera za rad. To znači da aplikacije koje šalju ili čitaju podatke mogu raditi brže, jer koriste više servera u isto vreme.
<br />
<br />
Kada se nova poruka posalje na topik , ona se dodaje na kraj jedne od particija. Poruke sa istim ključem događaja (npr. ID kupca ili vozila) upisuju se u istu particiju, a Kafka garantuje da će svaki potrošač određene particije topika uvek čitati događaje iz te particije tačno onim redosledom kojim su upisani. Bitno je naglasiti da se sve poruke unutar istog topika ne moraju citati redom, a unutar iste pariticije se citaju redom uvek. Na primer, postoje dve particije: particija0 i particija1. Prvo je poruka0 upisana u particiju0, zatim je poruka1 upisana u particiju1, a nakon toga je poruka2 upisana u particiju0. U ovom scenariju, poruka0 će uvek biti pročitana pre poruke2. Međutim, Kafka ne garantuje da će poruka1 biti pročitana pre ili posle poruke0.
<br />
<br />
Ako poruke ne sadrže ključ paritcijama se dodeljuju po round-robin algoritmu. Ako postoje ključevi hashFuncton(key). Ako postoje kljucevi particija na koju se svrstava poruka se određuje na sledeci nacin 
<br />Partition= hashFunction(key) mod numberOfPartitions
<br />
<br />Na ovaj način se omogućava da poruke sa istim ključem pripadnu istoj particiji.
Kafka podržava replikaciju, tj jedna particija se nalazi na više broker u clucaju otkaza jendog od njih.Gleda se uglavnom da budu na 3 brokera. Kada postoji 3 brokera, particije i njihove replike mogu se ravnomerno rasporediti, čime se izbegava preopterećenje jednog brokera.Ako koristiš 2 brokera, sistem nije otporan na kvarove oba brokera, što povećava rizik gubitka podataka.

**ZooKeeper**
On upravlja brokerima, tj klasterom. Dodaje i uklanja brokere iz klastera, određuje lider brokera i čuva konfiguracije topika.

**Consumer grupe**
su grupe korisnika koje ogranicavaju čitanje iz particija. Pravilo je da je jedna particija dodeljena na citanje samo jednom consumeru unuatr consumer grupe. Ovo pravilo osigurava da više conusmera u istoj grupi ne čita iste poruke…to znači dupla obrada podataka. Ako imamo više particija nego consumera, jedna consumer obrađuje poruke iz više particija. Ako je slučaj obrnut, tj da imamo više consumera nego particija , samo neki conumeri neće obrađivati poruke sa particija. Kada se grupi pridruzi novi conusmer (ili ode) dolazi do “rebalance”, tj particije se pono raspoređuju potrošačima. 

 **Kada želimo da consumeri budu u istoj grupi?**
<br /> Kada npr radimo paralanu obradu nekih podataka i želimo da jedna instanca servisa npr obradjuje jedne podatke a druga instanca istog servisa druge podatke, tj da se paralelizuje obrada podataka. Ako pak želimo da svi consumeri čitaju sve poruke stavicemo ih u razlicite consumer grupe i onda ce jedna conusmer morati da obradi sve particije. Npr yelimo da dva servisa koja obavljaju dve razylite funkcije citaju sa istog topika,npr. kada korisnik naruci nesto sa e-commerce sajta, taj dogadjaj se salje na topik i jedan servis npr smanjuje kolicinu proizvoda, a drugi vrši preporuku za tog korisnika.

## Opis demo projekta Apache Kafke i pub/sub mehanizma
<br />Ovim projektom se demonstrira instalacija i pokretanje kafke na Windows operativnom sistemu kao i kreiranje projekta. Projekat demonstrira rad pub/sub mehanizma i pokazuje na nekom osnovnom nivou primenu kafke u jednom e-commerce sistemu. Projekat se sastoji od producer servisa pod nazivom “event-producer” koji salje poruke o korisnikovim aktinostima. npr {userId=”1”,activity=”viewed”,”orderId=”2” }.
<br />Kljuc ove poruke ce biti userId. Ovi podaci se salju na topik “user-activity”.Topik cemo podeliti na dve particije. Sa tog topika ga citaju dva servisa koja imaju dve razlicite uloge, prvi ima ulogu sistema za davanje preporuka, a drugi ima ulogu za proveru prevara i bezbednosti. Ova dva sistema se nalaze u razlicitim consumer grupama i samim tim posto su jedine u svojim consumer grupama one primaju sve informacije i citaju podatke sa svih partciija. Time sto smo stavili da je userId kljuc poruke obezbdjujemo da ce podaci istog korisnika citati po redu jer ce biti u istoj particiji.Ovaj primer prikazuje “decoupling” sistema,obradu događaja u realnom vremenu (sistemi za preporuku npr odmah dobijaju informacije o korisnikovim akcijama i mogu pravi real-time preporuke kako bi se korisniku prikazivao sadrzaj koji ga interesuje)

<br />Kada je u pitanju aktivnost “ordered” poruke se salju i na topik “user-order”koji tako]e ima dve particije. Medjutim, conusmeri ce biti sad isti servisi (2 servisa) koji demonstriraju paralelnu obradu. Servisi ce se nalaziti u istoj consumer grupi i svakom od njih ce biti dodeljena particija. Na ovaj nacin svaki od njih ce obradjivati narudbine za konkretnog user-a. Ovo prikazuje paralelnu obradu, load balancing, skalabilnost(ako broj narudžbina naglo poraste, može se dodati još particija i consumer-a kako bi se poruke obradile bez usporenja)

Ova jednostvavna aplikacija ce pokazati konfiguraciju kafke, pariticonisanje topika i kako to particionisanje upotrebiti, upotreba consumer grupa kao i prikazivanje nekih od osnovnih use-caseva kafke

## Demonstracija rada Apache Kafke i njegovog pub-sub mehanizma
### Instalacija i namestanje konfiguracije Kafke (na Windows OS)
1. **Prvi korak je otići na zvaničnu dokumentaciju Apache Kafka i skunti poslednju verziju. https://www.apache.org/dyn/closer.cgi?path=/kafka/3.9.0/kafka_2.13-3.9.0.tgz**
2. **Sledeći korak je prebaciti folder na željenu lokaciju i tu ga ekstrakovati**
3. **Treba izmeniti konfiguraciju kafke. Prvo treba izemeniti server.properties, tj izmeniti putanju ka logs.dirs**
4. **Izmeniti putanju za dataDir**
5. **Otvoriti dva cmd i pozicionirati se unutar foldera gde ste sačuvali skinute podatke** 
6. **U prvom otkucati komandu “.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties” koja sluzi za pokretanje Zookeeper-a**
7. **U drugom otkucati komadnu “.\bin\windows\kafka-server-start.bat .\config\server.properties” koja služi za pokretanje broker-a.**
Ako vam se sve pokreće kako treba možemo krenuti sa kreiranjem SpringBoot aplikacije.

### Kreiranje  producer servisa
8. **Otići ćemo na sajt https://start.spring.io i kreirati naš producer servis sa sledećom konfiguracijom**
  ![image](https://github.com/user-attachments/assets/acd0d42a-222f-4d71-8b6e-98f1fa7e94e4)


9. **Biramo poslednju verziju Spring Boot-a koja nije SNAPSHOT, biramo Maven kao project menagement alat. Biramo naziv grupe, artifaktorija i projekta. Nakon toga ukljucujemo dependecy-je. Nama je bitan dependecy “Spring for Apache Kafka” koji nam omogućava rad sa Kafkom i Spring Web da bismo mogli da koristimo  REST prilikom testiranja rada naše aplikacije. Nakon toga kliknemo na dugme generate gde ce nam se kreirati pocetna verzija projekta i preuzeti.**

### Analiza unapred kreiranog koda radi demonstracije
10. **Za kreiranje aplikacije ja sam se odlucila za Intellij, ali moze se koristi bilo koji drugi IDE.**
11. **Otvoriti preuzet projekat i prvo sto se treba pogledati jeste application.properites**
```properties
spring.application.name=event-producer
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.client-id=user-activity-producer

spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
```

**spring.application.name**=event-producer Ovo ime se koristi za identifikaciju aplikacije(alati za praćenje, logiranje ili distribucija konfiguracija)
**spring.kafka.bootstrap-servers**=localhost:9092 naglasava adresu kafka brokera i broker koji cemo koristiti. Radi lokalno na portu 9092.Ovo omogućava aplikaciji da komunicira sa Kafka klasterom putem definisanih servera.
**spring.kafka.producer.client-id**=user-activity-producer : Identifikuje proizvođača unutar Kafka klastera.
**spring.kafka.producer.key-serializer**=org.apache.kafka.common.serialization.StringSerializer : Definiše koji serializer će se koristiti za serijalizaciju ključa poruke. StringSerializer pretvara ključeve poruka u niz karaktera (string)
**spring.kafka.producer.value-serializer**=org.springframework.kafka.support.serializer.JsonSerializer  : Definise koji serializer će se koristiti za serijalizaciju vrednosti poruke.JsonSerializer konvertuje Java objekte u JSON format pre nego što se pošalju u Kafka temu. 

Kafka očekuje da se svi podaci koji se šalju (ključevi i vrednosti poruka) konvertuju u binarni format (bajtove). To omogućava:

Efikasnu obradu i skladištenje podataka – Kafka interno radi s binarnim podacima.
Interoperabilnost, tj da podaci koju su kreirani u jednoj aplikaciji i mogu biti konzumirani u drugoj, čak i ako su napisane u različitim programskim jezicima.

12. **Kreiranje topika “user-activity” i “user-order”**
```java
package com.example.event_producer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {
    @Bean
    public NewTopic userActivityTopic(){
        return TopicBuilder.name("user-activity").partitions(2).build();
    }

    @Bean
    public NewTopic userOrdersTopic() {
        return TopicBuilder.name("user-order").partitions(2).build();
    }
}
```

Topici se kreiraju sa 2 particije i za njihovo kreiranje koristi se klase NewTopic i TopicBuilder iz Spring Kafka bilioteke. Klasa TopicBuilder koristi “Builder” pattern za kreiranje topika.<br /> Omogućava da se kroz kod kreira topik  bez potrebe za korišćenjem CLI komandi ili manuelne konfiguracije.To je klasa koja olaksava proces kreiranja i konfiguracije topika i pruzi api za kreiranje NewTopic objekta.Prilikom build() fje vraca NewTopic instancu i tada Spring Kafka koristi Spring Admin Api da kreira topik na serveru

13. **Kreiranje klase koja predstavlja poruku**

```java
    public ActivityMessage() {
    }

    public ActivityMessage(String userid, String productId, String activity) {
        this.userid = userid;
        this.productId = productId;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "ActivityMessage{" +
                "userid='" + userid + '\'' +
                ", productId='" + productId + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityMessage that = (ActivityMessage) o;
        return Objects.equals(userid, that.userid) && Objects.equals(productId, that.productId) && Objects.equals(activity, that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, productId, activity);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

}
```


14. **Kreiranje producer klase**


```java
@Service
public class UserActivityProducer {

    private final KafkaTemplate<String, ActivityMessage> kafkaTemplate;

    public UserActivityProducer(KafkaTemplate<String, ActivityMessage> template) {
        kafkaTemplate = template;
    }

    public void sendMessage(ActivityMessage message) {
        if (message.getActivity().equals("ordered")) {
            kafkaTemplate.send("user-orders", message.getUserid(), message);

        }
        kafkaTemplate.send("user-activity", message.getUserid(), message);
        System.out.println("User activity: " + message);
    }
}
```

Kafka template je genericka klasa za slanje poruka na topik. Prilikom njenog “injektovanja” u konsturktoru navodi se tip kljuca i tip poruke (String i ActivityMessage).KafkaTemplate se automatski konfiguriše preko Spring Boot Kafka startera koristeći application.yml ili application.properties.
<br /> Povezuje se sa konfiguracijama poput serlijalizatora. Spring automatski kreira KafkaTemplate bazirano na konfiguraciji, tako da je moguće direktno koristiti injekciju u klasi. Ova klasa omogućava jednostavno i fleksibilno upravljanje komunikacijom sa Kafka klasterom.
Ovde se koristi send metoda ciji su argumenti, topik na koji se poruka salje, id poruke i njeni podaci.

15. **Kreiranje controllera da bi mogao Postman da se koristi za demonstraciju slanja aktivnosti.**

```java
@RestController
public class UserActivityProducerController {
    private final UserActivityProducer producer;

    public UserActivityProducerController(UserActivityProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/sendToTopic")
    public void sendToTopic(@RequestBody ActivityMessage userActivity){
        producer.sendMessage(userActivity);
    }
}
```

16. **Kreiranje consumer aplikacije “recommendation-system”**
![image](https://github.com/user-attachments/assets/f4728e88-faec-4d5a-92fe-50968c833a1a)

17. **Pored ovog treba dodati dependency za deserilizaciju Json objekata u Java klase.**
   ```java
 <groupId>com.fasterxml.jackson.core</groupId>
   <artifactId>jackson-databind</artifactId>
   <version>2.15.2</version>
</dependency>
```

18.  **Namestamo konfiguraicju unuatar application.properites**

```properties
spring.application.name=recommendation-system
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=recommendation-system-consumer
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*
server.port=81
```

Namestamo id conusmer grupe i na koji nacin zelimo da se deserijalizuju poruke koje stizu sa kafke. Zelimo da se bajtovi za kljuc deserijalizuju u String ,a ya vrednost se koristi JsonDeserializer i posle toga se pomocu Jackson biblioteke mapira na ActivityMessage objekat.

19. **Kreiramo ActivityMessage klasu koja je ustiom paketu kao i klasa unutar producera. Mogli smo da stavimo klasu u jedan zajednicki paket i da je ukljucimo, ali posto je jedna klasa u pitanju odradicemo na ovaj nacin**
20. **Kreirajmo consumer-a**
```java
@Component
public class UserActivityConsumer {

    @KafkaListener(topics = "user-activity", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(ActivityMessage message) {
        System.out.printf(
                "Radi realtime preporuku za korisnika %s, \n zato što je odradio aktivnost %s \n nad proizvodom sa ID-jem: %s \n",
                message.getUserid(),
                message.getActivity(),
                message.getProductId()
        );    }

}
```

KafkaLisnere je deo Kafkinog ekosistema koji se koristi za asinhrono pracenje i obradu poruka koje dolaze sa navednog topika. Kafka listenr se “pretplati” na odredjeni topik i kada poruka stigne on je oprima i obradjuje. Pored topika i consumer grupe moze imati i broj niti kao argument

21. **Na identican nacin kreiramo i ostale conusmere samo ce unutar consumer servisa “security-and-fraud-detection” to biti i consumer group id. A unutar consumer servisa “order-processing-1” i “order-processing-2” , koji ce pripadati istoj consumer grupi, nalazi se group id “order-processing”. Na ovaj nacin recommendation-system i security-and-fraud-detection servisi pirmaju sve poruke , dok “order-processing-1” i “order-processing-2” servisi primaju poruke sa po jedne particije i jedan servis obrajduje podatke za jednu grupu korisnika, a drugi za drugu.**
    
22. **Slanje poruke iz Postman-a koja demonstrira odrađenu aktivnost od strane korisnika. Pogledati kako se poruke šalju na topike  i kako consumeri čitaju**

![image](https://github.com/user-attachments/assets/8df0d0fe-a12d-4f35-94f1-db5c1e62f1fa)

