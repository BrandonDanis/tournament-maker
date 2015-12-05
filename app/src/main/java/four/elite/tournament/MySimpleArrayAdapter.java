package four.elite.tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class MySimpleArrayAdapter extends ArrayAdapter{

    private final Context context;
    private final String[] names,teams,imageUrls;
    private final int[] rankings;

    public MySimpleArrayAdapter(Context context, String[] names, String[] teams, int[] ranking, String[] imageUrls) {
        super(context, -1, names);
        this.context = context;

        this.names = names;
        this.rankings = ranking;
        this.teams = teams;
        this.imageUrls = imageUrls;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ImageLoader imageLoader = ImageLoader.getInstance();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView nameLabel = (TextView)rowView.findViewById(R.id.firstLine);
        TextView teamNameLabel = (TextView)rowView.findViewById(R.id.description);
        TextView rankingLabel = (TextView)rowView.findViewById(R.id.rankingLabel);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.icon);

        nameLabel.setText(names[position]);
        teamNameLabel.setText(teams[position]);
        if(rankings[position] == 0){
            rankingLabel.setText("");
        }else{
            rankingLabel.setText("Rank: " + Integer.toString(rankings[position]));
        }

        imageLoader.displayImage(imageUrls[position], imageView);


        return rowView;

    }


}
