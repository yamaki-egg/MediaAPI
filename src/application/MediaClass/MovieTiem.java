package application.MediaClass;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class MovieTiem extends Slider implements IMediator<MediaPlayBase>{
	
	private MediaPlayBase mediaPlayBase;
	private Label timeLabel=new Label();
	private HBox root=new HBox(5.0);
	public MovieTiem() {
		super();
		setMin(0);
		setValue(0);
		root.getChildren().add(this);
		root.getChildren().add(timeLabel);
		

	}
	public void SendData(MediaPlayBase media) {
		setMin(media.GetMedipPlayer().getStartTime().toSeconds());
		setMax(media.GetMedipPlayer().getStopTime().toSeconds());
		mediaPlayBase=media;
		SliderChanged();
		media.CurrentTimeEvent(this, timeLabel);
	}
	private void SliderChanged() {
        // スライダーの値が変化したときに再生位置を変更する
		addEventFilter(MouseEvent.MOUSE_RELEASED, e->{
			mediaPlayBase.GetMedipPlayer().seek(javafx.util.Duration.seconds(getValue()));
		});
		addEventFilter(MouseEvent.MOUSE_DRAGGED, e->{
			mediaPlayBase.GetMedipPlayer().seek(javafx.util.Duration.seconds(getValue()));
		});
		setOnMouseClicked(e->{
			mediaPlayBase.GetMedipPlayer().seek(javafx.util.Duration.seconds(getValue()));
			setValue(mediaPlayBase.GetMedipPlayer().getCurrentTime().toSeconds());
		});

	}
	@Override
	public HBox GetRoot() {
		// TODO 自動生成されたメソッド・スタブ
		return root;
	}

}
