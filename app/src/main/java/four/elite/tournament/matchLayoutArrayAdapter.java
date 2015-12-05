package four.elite.tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MatchLayoutArrayAdapter extends ArrayAdapter{

    Context context;

    List<Game> matches;

    public MatchLayoutArrayAdapter(Context context, List<Game> matches) {
        super(context, -1, matches.size());
        this.context = context;
        this.matches = matches;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageLoader imageLoader = ImageLoader.getInstance();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.match_layout, parent, false);

        TextView homeTeamScore = (TextView)rowView.findViewById(R.id.homeTeamScore);
        TextView awayTeamScore = (TextView)rowView.findViewById(R.id.awayTeamScore);
        TextView homePlayerName = (TextView)rowView.findViewById(R.id.homePlayerName);
        TextView awayPlayerName = (TextView)rowView.findViewById(R.id.awayPlayerName);
        ImageView homeImage = (ImageView)rowView.findViewById(R.id.homeTeamImage);
        ImageView awayImage = (ImageView)rowView.findViewById(R.id.awayTeamImage);

        homeTeamScore.setText(matches.get(position).getHomeTeam().getTeamName());
        awayTeamScore.setText(matches.get(position).getAwayTeam().getTeamName());

        homePlayerName.setText(matches.get(position).getHomeTeam().getName());
        awayPlayerName.setText(matches.get(position).getAwayTeam().getName());


        imageLoader.displayImage(matches.get(position).getHomeTeam().getImageUrl(), homeImage);
        imageLoader.displayImage(matches.get(position).getAwayTeam().getImageUrl(),awayImage);

        return rowView;

    }


}
