# [Semesteroppgave 1: “Rogue One oh one”](https://retting.ii.uib.no/inf101.v18.sem1/blob/master/SEM-1.md)


* **README**
* [Oversikt](SEM-1.md) – [Praktisk informasjon 5%](SEM-1.md#praktisk-informasjon)
* [Del A: Bakgrunn, modellering og utforskning 15%](SEM-1_DEL-A.md)
* [Del B: Fullfør basisimplementasjonen 40%](SEM-1_DEL-B.md)
* [Del C: Videreutvikling 40%](SEM-1_DEL-C.md)

Dette prosjektet inneholder [Semesteroppgave 1](SEM-1.md). Du kan også [lese oppgaven online](https://retting.ii.uib.no/inf101.v18.oppgaver/inf101.v18.sem1/blob/master/SEM-1.md) (kan evt. ha små oppdateringer i oppgaveteksten som ikke er med i din private kopi).

**Innleveringsfrist:**
* Del A + minst to deloppgaver av Del B skal være ferdig til **fredag 9. mars kl. 2359**. 
* Hele oppgaven skal være ferdig til **fredag 16. mars kl. 2359** ([AoE](https://www.timeanddate.com/worldclock/fixedtime.html?msg=4&iso=20180316T2359&p1=3399))
* [Ekstra tips til innlevering](https://retting.ii.uib.no/inf101/inf101.v18/wikis/innlevering)

(Kryss av under her, i README.md, så kan vi følge med på om du anser deg som ferdig med ting eller ikke. Hvis du er helt ferdig til den første fristen, eller før den andre fristen, kan du si fra til gruppeleder slik at de kan begynne å rette.)

**Utsettelse:** Hvis du trenger forlenget frist er det mulig å be om det (spør gruppeleder – evt. foreleser/assistenter hvis det er en spesiell situasjon). Hvis du ber om utsettelse bør du helst være i gang (ha gjort litt ting, og pushet) innen den første fristen.
   * Noen dagers utsettelse går helt fint uten begrunnelse, siden oppgaven er litt forsinket.
   * Hvis du jobber med labbene fremdeles, si ifra om det, og så kan du få litt ekstra tid til å gjøre ferdig labbene før du går i gang med semesteroppgaven. Det er veldig greit om du er ferdig med Lab 4 først.
   * Om det er spesielle grunner til at du vil trenge lengre tid, så er det bare å ta kontakt, så kan vi avtale noe. Ta også kontakt om du [trenger annen tilrettelegging](http://www.uib.no/student/49241/trenger-du-tilrettelegging-av-ditt-studiel%C3%B8p). 
   

# Fyll inn egne svar/beskrivelse/kommentarer til prosjektet under
* Levert av:   *NAVN* (*BRUKERNAVN*)
* Del A: [x] helt ferdig, [] delvis ferdig
* Del B: [x] helt ferdig, [] delvis ferdig
* Del C: [x] helt ferdig, [ ] delvis ferdig
* [x] hele semesteroppgaven er ferdig og klar til retting!
- Håper du liker den :)

# Del A
## Svar på spørsmål

a) Hva vil du si utgjør tilstanden til objekter som implementerer de nevnte grensesnittene?  (F.eks. hvis du ser på ILocation så vil du gjerne se at ILocation-objekter må ha en tilstand som inkluderer x- og y-koordinater – selv om de sikkert kan lagres på mange forskjellige måter. De må også vite om eller være koblet til et IArea, siden en ILocation ser ut til å “vite” hvilke koordinater som er gyldige.)
- Det jeg tror:
- ILocation må ha x- og y-kordinater og en IArea
- IGrid må ha et object <T> som er lagret i en celle. Og en ILocation
- IMultiGrid må ha en liste med objecter <T> som er lagret i en celle.
- IItem må ha: currentHP, maxHP, Defense, HealthStatus?, Name, Et unikt symbol, 
- Andre ting er jeg ikke helt sikker på ennå for har ikke sett på koden
G

b) Hva ser ut til å være sammenhengen mellom grensesnittene? Flere av dem er f.eks. laget slik at de utvider (extends) andre grensesnitt. Hvem ser ut til å ta imot / returnere objekter av de andre grensesnittene?
- Sånn jeg tror det er (Kanskje alt går under IGame?):
- IItem:
	- IActor
		- IPlayer
		- INonPlayer
	- Wall
- IGame: ?
- IMapView, IGameMap:
	- IGrid, MultiGrid:
		- IArea:
			- ILocation

c) Det er to grensesnitt for kart, både IGameMap og IMapView. Hvorfor har vi gjort det slik?
- Jeg ser for meg at IGameMap holder hele kartet og alt som skjer,
- mens IMapView er bare det som IPlayer ser og at denne flytter seg rundt på IGameMap etterhvert
- som IPlayer beveger seg.

d) Hvorfor tror du INonPlayer og IPlayer er forskjellige? Ville du gjort det annerledes?
- Jeg tror de er forskjellige fordi IPlayer skal kunne bevege seg selv og derfor trenger en
- keyPressed method. Dette kan ikke INonPlayer ha fordi vi ville ikke styre fienden selv.
- Jeg antar at jeg ville gjort det på samme måte selv hvis jeg hadde husket å tenkt så langt.

e) Stemmer implementasjonen overens med hva du tenkte om tilstanden i Spørsmål 1 (over)? Hva er evt. likt / forskjellig?
- Stort sett ja, men jeg trodde de ville være feltvariabler og ikke metoder. Pluss at jeg ikke ser noen konstruktør, så
- da må alle kaniner og gulrøtter være helt like. Det er forsåvidt greit. 
- De hadde også en size som jeg glemte å tenke på. Antok bare at dette ikke var implementert i koden, men i en texture.
- Pluss at de hadde en food feltvariabel. Det er jo ikke åpenbart, men har jo med spillet sine regler å gjøre.

f) Hvordan finner Rabbit ut hvor den er, hvilke andre ting som er på stedet og hvor den har lov å gå?
- Dette er fordi at Game kaller på currentActor.doTurn(). Da har Game allerede lagret hvor currentActor er. 
- Derfor har Game tilgang på hvor Rabbit er når rabbit i sin doTurn() metode kaller game.getLocalItems(), game.canGo()
- og game.move().

g) Hvordan vet Game hvor Rabbit er når den spør / hvordan vet Game hvilken Rabbit som kaller getLocation()?
- Game vet hvilken rabbit som kaller getLocation() fordi den har kjørt currentActor.doTurn() og midlertidig lagret
- feltvariablene til currenctActor slik at Game kan bruke disse i løpet av den turn.

# Del B
## Svar på spørsmål

a)  Du har måttet gjøre en del arbeid med nabo-celler og slikt. Håndterer du dette på en annen måte enn vi gjorde i labbene (f.eks. cell-automatene og labyrinten)? Hva synes du er mest praktisk?
- Jeg håndterer det vell på omtrent samme måte, men på et litt høyere nivå ved hjelpemetoder istedenfor bare å bruke x- og y-kordinater slik som før.

b) Hvorfor går de fleste av spill-"trekkene" (slik som at noen flytter seg, plukker en ting, legger ned en ting, angriper naboen, etc.) gjennom Game? Kan du se for deg fordeler / ulemper ved dette?
- Fordelen er at man har en "sjef" som har oversikt over kartet, alle objectene som fyller de ulike rutene der og derfor kan interagere med andre ruter gjennom metoder. Ulempe blir vell kanskje at det burde vært flere "skjermer". Jeg tror kanskje det kunne vært en idé å ha en "gameScreen", "InventoryScreen", "MenuScreen" for å gjøre det litt lettere, men det blir jo et helt annet design, så jeg orker ikke implementere det i dette programmet fordi det blir for mye å endre på.

c) I en del av metodene burde vi kanskje vært litt mer presise på hva som er “forkravene” – hva må være oppfylt for at det skal gå bra å kalle denne metoden? Vi har nevnt litt at game.move() krever at feltet i retningen er lovlig og ledig. Se gjennom de andre metodene i Game og GameMap og se om de har spesielle antakelser rundt parameterne. Ser du noe som burde endres? F.eks., enten metoder som sjekker for ting som kan gå galt, men ikke sier noe om forutsetningene i dokument – eller metoder som burde sjekket parameterne sine / andre forutsetninger. F.eks., hva med Game.addItem()  (og drop() også, forsåvidt) – bør den f.eks. sjekke at feltet ikke allerede er opptatt (f.eks. legge til ny IActor når det allerede er en IActor der, e.l.)?
- Ja, det er nok mye rusk her og der hvis man går inn i alle detaljene. Hvis vi skulle laget et kommersielt spill tror jeg at vi burde gjort alt idiotsikkert og passet på at ingen metoder har mulighet for å gjøre noe galt. Men siden dette gjerne tar lang tid (spesielt hvis man ikke har laget hele koden selv), så fungerer det som oftest å tilpasse koden slik man passer på at sånne ting ikke skjer. 

d) Tenker du annerledes om noen av spørsmålene fra Del A nå?
- GameMap og IMapView var egentlig ikke så forskjellige. Forskjellen var hovedsakelig bare noen få ekstra-metoder i GameMap som bare kan kalles fra game.

# Del C
## Oversikt over designvalg og hva du har gjort
Så jeg har gjort en del ting:

1. Jeg droppet vision-greiene fordi jeg følte ikke det trengtes i spillet. Jeg liker at enemies er litt dumme siden kartet er lite og det fort kan bli litt tett.

2. Jeg addet masse emojier. De er enten food, enemy, armor eller weapon. Enemy går random rundt, men angriper hvis den ser en spiller i direkte naborute. Food, armor og weapon er i teorien helt like, men jeg lot de alle arve fra emoji sånn at det var lettere å skille mellom de. De blir laget i addItemToMap() metoden min i game som basically styrer det med å plassere de på kartet. 

3. Man er også en emoji. Den endrer seg basert på hvor mye hp den har og hvis den har på solbriller. Den gir også et blunkefjes hvis man vinner.

4. Jeg har implementert leveling. Det er ganske enkelt og fungerer sånn at man levler for annenhver fiende man dreper og man får +3 defense og attack per level.

5. Jeg har gitt Player tre forskjellige Containere som holder food, armor og weapon. De har også en del kule metoder i seg som gjør at de passer bedre enn å bare bruke en arrayList.

6. Containerne er nå generiske.

7. Jeg la til muligheten for å legge fra seg weapon med "w" og armor med "a". Det er begrenset plass i disse, så følte det var nødvendig. 

7. Jeg la til farge på noen av emojiene, men det var litt få fargevalg, så det ble sånn passe. Skulle gjerne hatt litt flere valg. Pluss at spillebrettet gikk over i svart/hvit da jeg fikset på emojiene. Jeg syns ikke det var så farlig egentlig fordi det ser fortsatt ganske bra ut, bare annerledes.

8. Jeg la til en finalboss klasse. Den extender Enemy, men har egne verdier og resten av spillet interagerer litt annerledes med den for eksempel når den blir laget og dør.

9. Når FinalBoss dør har man vunnet spillet. Da dropper den en medalje på stedet der den døde. Hvis man plukker opp denne får man beskjed om at man vant spillet og emojien blunker.

10. Nå dropper også griser bacon når de dør... random.

11. Jeg laget en displayInfo() metode som kjøres hver gang det er spilleren sin tur. Den viser relevant info til høyre på skjermen som hp, level, attack, defense, antall food items, diverse armor og weapons equiped. Man kan ha 6 armor og 2 weapons samtidig.

12. Stort sett alt :)




 
