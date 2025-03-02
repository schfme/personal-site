package me.schf.personal.controller.content.music;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import me.schf.personal.controller.content.ContentProvider;

@Component
public class DummyMusicProvider implements ContentProvider<Music> {

	@Override
	public List<Music> getContent() {
		Music track1 = new Music("placeholder track 1", LocalDate.now().minusYears(1), "placeholder-track-1");
		Music track2 = new Music("placeholder track 2", LocalDate.now().minusYears(5), "placeholder-track-2");
		Music track3 = new Music("placeholder track 3", LocalDate.now().minusYears(10), "placeholder-track-3");

		return Arrays.asList(track1, track2, track3);
	}

}
