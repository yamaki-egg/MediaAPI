package application.MediaClass;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

public class TimeSlider extends Slider implements Mediator<MediaPlayAddEventLisnerBase>{
	
	private MediaPlayer mediaPlay;
	private final Label timeLabel=new Label("再生時間");
	private HBox root=new HBox(5.0);
	private MediaPlayAddEventLisnerBase mediaPlayAddEventLisner;
	public TimeSlider() {
		super();
		setMin(0);
		setValue(0);
		root.getChildren().addAll(timeLabel,this);
	}

	public void ifColleagueChanged(MediaPlayAddEventLisnerBase mediaPlayAddEventLisner) {
		this.mediaPlay=mediaPlayAddEventLisner.getMediaPlayer();
		this.mediaPlayAddEventLisner=mediaPlayAddEventLisner;
		setMin(this.mediaPlay.getStartTime().toSeconds());
		setMax(this.mediaPlay.getStopTime().toSeconds());
		setSnapToTicks(true);
		sliderChanged();
		mediaPlayAddEventLisner.currentTimeEvent(this, timeLabel);
	}

	private void sliderChanged() {
        // スライダーの値が変化したときに再生位置を変更する
		addEventFilter(MouseEvent.MOUSE_RELEASED, e->{
			this.mediaPlay.seek(javafx.util.Duration.seconds(getValue()));
		});
		addEventFilter(MouseEvent.MOUSE_DRAGGED, e->{
			this.mediaPlay.seek(javafx.util.Duration.seconds(getValue()));
		});
		setOnMouseClicked(e->{
			this.mediaPlay.pause();
			this.mediaPlay.seek(javafx.util.Duration.seconds(getValue()));
			this.mediaPlay.play();
		});

	}
	@Override
	public HBox getRoot() {
		// TODO 自動生成されたメソッド・スタブ
		return root;
	}

}
