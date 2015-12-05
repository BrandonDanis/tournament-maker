package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PlayerCreation extends AppCompatActivity {

    TextView playerNameLabel;
    AutoCompleteTextView teamNameLabel;
    ImageView teamImage;
    String currentImageString = null;

    String[] teams = {"Spain","Brazil","France","Germany","Argentina","Italy","England","Portugal","Netherlands","Belgium","Turkey","Russia","Colombia","Serbia","Switzerland","Ivory Coast","Denmark","Sweden","Cameroon","Uruguay","Poland","Senegal","Chile","Greece","Mexico","Croatia","Republic of Ireland","United States","Nigeria","Algeria","Paraguay","Morocco","Slovenia","Ukraine","Scotland","Bosnia Herzegovina","Czech Republic","Austria","Ghana","Slovakia","Korea Republic","Romania","Venezuela","South Africa","Wales","Saudi Arabia","Japan","Ecuador","Costa Rica","Togo","Norway","DR Congo","Northern Ireland","Iceland","Finland","Tunisia","Gabon","Guinea","Peru","Mali","Armenia","Egypt","Bulgaria","Montenegro","Mozambique","Georgia","Albania","Australia","Sierra Leone","Canada","Uzbekistan","Hungary","Congo","Cape Verde Islands","Israel","Syria","Burkina Faso","Cuba","Benin","Estonia","FYR Macedonia","Angola","Guinea Bissau","Haiti","Liberia","Kenya","New Zealand","Bermuda","Fiji","Montserrat","Jordan","Azerbaijan","Seychelles","St Kitts Nevis","San Marino","World","Malta","Uganda","Suriname","Grenada","Solomon Islands","Sudan","Liechtenstein","Indonesia","Faroe Islands","Mauritius","Chinese Taipei","Dominican Republic","Somalia","Qatar","Namibia","Tanzania","St Lucia","Libya","Antigua &amp; Barbuda","El Salvador","Burundi","India","Rwanda","Equatorial Guinea","Comoros","Puerto Rico","Kuwait","Belarus","Iran","Barbados","Guatemala","Iraq","Korea DPR","Panama","Bolivia","Zambia","Cyprus","Guam","Bahrain","Oman","Honduras","Guyana","Turkmenistan","Thailand","Lithuania","Cambodia","Niger","Philippines","Latvia","Netherlands Antilles","Gambia","Lebanon","Mauritania","CAR","Madagascar","Zimbabwe","Moldova","Palestinian Authority","New Caledonia","Kosovo","Kazakhstan","Trinidad &amp; Tobago","Jamaica","China PR","Luxemburg","Legends","FC Barcelona","FC Bayern","Napoli","Roma","PSG","Manchester City","Arsenal","Real Madrid","Inter","Everton","Spurs","Bor. Dortmund","Juventus","Milan","Manchester United","Sevilla FC","Liverpool","Valencia CF","FC Porto","Atlético Madrid","Lazio","Sporting CP","VfL Wolfsburg","Villarreal CF","Chelsea","Olympique Lyon","Bor. M&#39;gladbach","FC Schalke 04","Real Sociedad","FC Shakhtar","SL Benfica","Stoke City","Beşiktaş JK","Marseille","Fenerbahçe SK","Athletic Bilbao","Southampton","Swansea City","Zenit","Bayer 04","Torino","Newcastle Utd","Fiorentina","CSKA Moskva","Sunderland","RC Deportivo","Genoa","Eint. Frankfurt","Rayo Vallecano","Ajax","Lokomotiv","AS Monaco","West Ham","Olympiakos CFP","Málaga CF","PSV","West Brom","Galatasaray SK","1899 Hoffenheim","Crystal Palace","LOSC Lille","Atlético Mineiro","Hamburger SV","RCD Espanyol","Udinese","Hertha BSC","Hannover 96","Celta Vigo","Bordeaux","Palmeiras","FC Krasnodar","Aston Villa","Getafe CF","Stade Rennais","Getafe CF","1. FC Köln","Aston Villa","Sampdoria","VfB Stuttgart","Atalanta","Cruzeiro","Feyenoord","FC Augsburg","Boca Juniors","Levante UD","River Plate","Real Betis","Leicester City","Saint-Etienne","Spartak Moskva","1. FSV Mainz 05","SC Braga","RSC Anderlecht","Hellas Verona","Chievo Verona","Bologna","Vit. Guimarães","Granada CF","Dinamo Moskva","Sassuolo","Palermo","Sounders FC","OGC Nice","São Paulo","Norwich City","FC Nantes","Werder Bremen","Monterrey","Tigres","Rubin Kazan","Al-Nassr","FC Lorient","Grêmio","Bursaspor","Toulouse FC","Internacional","Colo-Colo","NY City FC","Sporting Gijón","Montpellier HSC","Belenenses","QPR","CS Marítimo","Bournemouth","Colo-Colo","Belenenses","LA Galaxy","Club Brugge","NY City FC","Sporting Gijón","Montpellier HSC","Bournemouth","CD Nacional","Independiente","Fluminense","Stade Reims","FC København","PAOK","UD Las Palmas","Al-Hilal","Racing Club","SD Eibar","UD Almería","Montreal Impact","Orlando Pirates","Real Valladolid","Empoli","Sheffield Wed","Cardiff City","Hull City","Toluca","Panathinaikos","KAA Gent","América","FC Twente","Columbus Crew","RB Salzburg","Middlesbrough","Santos","Uni. de Chile","Rosario Ctral. ","Estoril-Praia","SC Freiburg","Atlético Paranaense","Cagliari","Ulsan Hyundai","Kaizer Chiefs","SM Caen","Rio Ave FC","Sivasspor","Jeonbuk FC","Vitesse","Newell&#39;s","Kuban Krasnodar","Estoril-Praia","Reading","SM Caen","Cagliari","Jeonbuk FC","Ulsan Hyundai","Toronto FC","FC Sion","Querétaro","AS Nancy","Club León","Córdoba CF","Santos Laguna","Monarcas","FC Metz","Tijuana","Deport. Alavés","Livorno","Brøndby IF","Ind. Santa Fe","Derby County","Grasshopper","Charlton Ath","Sint-Truidense","KVC Westerlo","Standard Liège","KRC Genk","Fulham","Kalmar FF","Vålerenga","CD Tenerife","Wanderers","Brentford","Al-Shabab","Al-Ittihad","Burnley","Gaziantepspor","Terek Grozny","Frosinone","Estudiantes","Anzhi Makhach.","SJ Earthquakes","New England","Whitecaps FC","Figueirense","FC Dallas","Boavista","Philadelphia","Ponte Preta","Sporting KC","Estudiantes","KVC Westerlo","Tondela","Portland","ES Troyes","Ind. Santa Fe","Wanderers","Sporting KC","Córdoba CF","Anzhi Makhach.","Konyaspor","New England","Burnley","Derby County","Querétaro","Eskişehirspor","Boavista","St. Johnstone","Leyton Orient","GwangJu FC","Argentinos Jrs.","Górnik Zabrze","SV Ried","Chesterfield","Ross County","Wisła Kraków","US Créteil","Inverness CT","Tromsø IL","Vercelli","Örebro SK","Nîmes Olympique","Daejeon Citizen","Cúcuta Depor.","Depor. Iquique","San Marcos","Uniautónoma FC","Aarhus GF","Dundee FC","De Graafschap","Perth Glory","Ruch Chorzów","Aldosivi","FC Vaduz","Unión La Calera","Jaguares de Córdoba","CD Antofagasta","Plymouth Argyle","Southend United","Hobro IK","Piast Gliwice","Lillestrøm SK","Portsmouth","Well. Phoenix","San Martín de San Juan","Peterborough","Salernitana","Royal Mouscron","Dundee United","Luton Town","Deportivo Pasto","Dorados de Sinaloa","Lanciano","FK Haugesund","CD Palestino","Jeju United FC","Red Star FC","Cham. Niortais","SV Sandhausen","Pogoń Szczecin","Stade Lavallois","FC Thun","Sheffield Utd","Latina","Colchester","Roda JC","Esbjerg fB","Millwall","La Equidad","Rafaela","Brescia","Excelsior","Halmstads BK","Nueva Chicago","Rotherham Utd","FSV Frankfurt","Bahía Blanca","SK Sturm Graz","UE Llagostera","SønderjyskE","Motherwell","Patriotas FC","MSV Duisburg","Adelaide United","Terni","Águilas Doradas","Al. Petrolera","Notts County","Viking FK","Kilmarnock","Start","Aalesunds FK","Valenciennes FC","Trapani","CD Mirandés","Central Coast","Sandefjord","Morecambe","Carlisle United","Burton Albion","Al Wehdah","Wycombe","Bourg en Bresse 01","Cork City","Hartlepool","Falkenbergs FF","Najran","Åtvidabergs FF ","Oxford United","Drogheda Utd","Gefle IF","Górnik Łęczna","Admira","SV Mattersburg","SV Grödig","Dundalk","Drogheda Utd","Cork City","St. Pats","Sligo Rovers","Bohemians FC","Derry City","Galway United","Limerick","Adelaide United","Well. Phoenix","Perth Glory","Newcastle Jets","Central Coast","Plymouth Argyle","Portsmouth","Leyton Orient","Notts County","Bray Wanderers","Peterborough","Rosenborg BK","Aalesunds FK","Tromsø IL","Lillestrøm SK","Mjøndalen IF","Boyacá Chicó","Cortuluá","Shrewsbury","Doncaster","Rochdale","Crucero del Norte","Temperley","Termalica Bruk-Bet Nieciecza","Bury","Gillingham","Cracovia","Northampton","SCR Altach","Cambridge Utd","Hajer","Unión Santa Fe","Zagłębie Lubin","Viborg FF","Barnsley","Walsall","Partick Thistle","Wolfsberger AC","Hearts","Mjøndalen IF","Blackpool","Colchester","Millwall","Sheffield Utd","Dagenham","Wigan Athletic","Górnik Zabrze","Piast Gliwice","Ruch Chorzów","Zagłębie Lubin","Cracovia","Termalica Bruk-Bet Nieciecza","Korona Kielce","Podbeskidzie","Górnik Łęczna","Chesterfield","SC Cambuur","FC Nordsjælland","Randers FC","Odense Boldklub","Aalborg BK","Esbjerg fB","SønderjyskE","Aarhus GF","Hobro IK","Viborg FF","1. FC Nuremberg","Guadalajara","Colorado Rapids","Suwon Bluewings","Bari","Puebla","Melb. Victory","Malmö FF","MK Dons","RCD Mallorca","FC Seoul","FC Sochaux","CA Osasuna","Chiapas","D.C. United","Strømsgodset IF","Clermont Foot","Audax Italiano","Veracruz","NY Red Bulls","Envigado","ADO Den Haag","Preston","Evian Thonon FC","IFK Norrköping","GIF Sundsvall","Gefle IF","IFK Norrköping","Helsingborgs IF","Djurgårdens IF","Hammarby","BK Häcken","IF Elfsborg","Halmstads BK","Örebro SK","Åtvidabergs FF ","Bristol City","Heracles Almelo","Darmstadt","Colón","Godoy Cruz","Quilmes","Rafaela","Nueva Chicago","Bahía Blanca","Argentinos Jrs.","San Martín de San Juan","Aldosivi","Sarandí","Fl. Varela","Huracán","GFC Ajaccio","Angers SCO","G. La Plata","Banfield","Vélez Sarsfield","Tigre","Belgrano","Sarmiento","Lanús","Unión Santa Fe","Crucero del Norte","Temperley","Amkar Perm","Mordovia Saransk","FC Ufa","FC Ural","FC Groningen","FC Utrecht","PEC Zwolle","Willem II","ADO Den Haag","Krylya Sovetov","Çaykur Rizespor","Gençlerbirliği","Darmstadt","Vitória FC","Krylya Sovetov","Blackburn Rvrs","Molde FK","FC Utrecht","Nott&#39;m Forest","G. La Plata","Atl. Nacional","Bolton","Jeonnam Dragons","Sport. Lokeren","Aalborg BK","Albacete","KV Kortrijk","Waasl.-Beveren","Sarpsborg","CD Huachipato","Stabæk Fotball","Bristol City","Deportes Tolima","FC St. Pauli","SD Huesca","Stade Brest","FK Austria","Seongnam FC","Sarandí","Colón","Real Oviedo","Perugia","CD Numancia","Paris FC","Bradford City","Hammarby","N.E.C.","Al-Raed","Quilmes","Unión Española","Melbourne City","Huddersfield","IF Elfsborg","SC Cambuur","AC Ajaccio","Birmingham City","Gimnàstic","CD Cobresal","Lechia Gdańsk","Jagiellonia","Heracles Almelo","Godoy Cruz","Pohang Steelers","BK Häcken","Dijon FCO","Pescara","FC St. Gallen","FK Bodø/Glimt","Tours FC","Incheon United","Djurgårdens IF","Çaykur Rizespor","VfL Bochum","Greuther Fürth","WS Wanderers","AD Alcorcón","Modena","Mordovia Saransk","Atlético Huila","Karlsruher SC","Arm. Bielefeld","CD O&#39;Higgins","Śląsk Wrocław","Huracán","Brisbane Roar","Cesena","1860 Munich","FC Luzern","FC St. Gallen","FC Thun","FC Vaduz","FC Lugano","KV Oostende","Sport. Lokeren","Zulte-Waregem","CD Palestino","Depor. Iquique","San Marcos","Unión La Calera","CD Antofagasta","Al-Fateh","Al-Taawoun","Al-Raed","Hajer","Unión Española","CD Cobresal","CD Huachipato","OHL","KV Kortrijk","Waasl.-Beveren","Royal Mouscron","Uni. Católica","San Luis de Quillota","Novara","Crotone","Odense Boldklub","Union Berlin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_creation);

        playerNameLabel = (TextView)findViewById(R.id.playerNameLabel);
        teamNameLabel = (AutoCompleteTextView)findViewById(R.id.teamNameLabel);

        final ImageLoader imageLoader = ImageLoader.getInstance();
        teamImage = (ImageView)findViewById(R.id.teamImage);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, teams);

        teamNameLabel.setThreshold(2);
        teamNameLabel.setAdapter(adapter);

        teamNameLabel.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Arrays.asList(teams).contains(teamNameLabel.getText().toString())){
                    String url = DataManager.getTeamImageUrlWithName(getApplicationContext(),teamNameLabel.getText().toString());
                    imageLoader.displayImage(url, teamImage);
                    currentImageString = url;
                }
            }

        });

    }

    public void cancelButtonClicked(View v){
        finish();
    }

    public void tryToAddUser(View v){

        if(playerNameLabel.getText().toString().trim().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Player name not valid", Toast.LENGTH_LONG);
            toast.show();
        }else if(teamNameLabel.getText().toString().trim().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Team name not valid", Toast.LENGTH_LONG);
            toast.show();
        }else{

            Intent intent = new Intent();

            intent.putExtra("Player Name",playerNameLabel.getText().toString());
            intent.putExtra("Team Name",teamNameLabel.getText().toString());
            intent.putExtra("Image URL", currentImageString);

            setResult(200, intent);
            finish();

        }

    }

}
