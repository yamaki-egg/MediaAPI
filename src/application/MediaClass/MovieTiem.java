package application.MediaClass;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

public class MovieTiem extends Slider implements IMediator<MediaPlayAddEventLisner>{
	
	private MediaPlayer mediaPlay;
	private final Label timeLabel=new Label("再生時間");
	private HBox root=new HBox(5.0);
	public MovieTiem() {
		super();
		setMin(0);
		setValue(0);
		root.getChildren().addAll(timeLabel,this);
	}
//	public void SendData(MediaPlayBase media) {
//		setMin(media.GetMedipPlayer().getStartTime().toSeconds());
//		setMax(media.GetMedipPlayer().getStopTime().toSeconds());
//		mediaPlayBase=media;
//		SliderChanged();
//		media.CurrentTimeEvent(this, timeLabel);
//	}
	public void SendData(MediaPlayAddEventLisner mediaPlayAddEventLisner) {
		this.mediaPlay=mediaPlayAddEventLisner.getMediaPlayer();
		setMin(this.mediaPlay.getStartTime().toSeconds());
		setMax(this.mediaPlay.getStopTime().toSeconds());
		SliderChanged();
		mediaPlayAddEventLisner.CurrentTimeEvent(this, timeLabel);
	}
//	private void SliderChanged() {
//        // スライダーの値が変化したときに再生位置を変更する
//		addEventFilter(MouseEvent.MOUSE_RELEASED, e->{
//			mediaPlay.GetMedipPlayer().seek(javafx.util.Duration.seconds(getValue()));
//		});
//		addEventFilter(MouseEvent.MOUSE_DRAGGED, e->{
//			mediaPlay.GetMedipPlayer().seek(javafx.util.Duration.seconds(getValue()));
//		});
//		setOnMouseClicked(e->{
//			mediaPlay.GetMedipPlayer().seek(javafx.util.Duration.seconds(getValue()));
//			setValue(mediaPlay.GetMedipPlayer().getCurrentTime().toSeconds());
//		});
//
//	}
	private void SliderChanged() {
        // スライダーの値が変化したときに再生位置を変更する
		addEventFilter(MouseEvent.MOUSE_RELEASED, e->{
			this.mediaPlay.seek(javafx.util.Duration.seconds(getValue()));
		});
		addEventFilter(MouseEvent.MOUSE_DRAGGED, e->{
			this.mediaPlay.seek(javafx.util.Duration.seconds(getValue()));
		});
		setOnMouseClicked(e->{
			this.mediaPlay.seek(javafx.util.Duration.seconds(getValue()));
			setValue(this.mediaPlay.getCurrentTime().toSeconds());
		});

	}
	@Override
	public HBox GetRoot() {
		// TODO 自動生成されたメソッド・スタブ
		return root;
	}

}
