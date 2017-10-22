package Shape;

import java.util.ArrayList;

import Window.Window;

public class ColorText extends Shape {

	public ArrayList<Text> textList;
	public String now_text = "";
	public float stepChar = 1.2f;
	public ColorText(Window window) {
		super(window);
		textList = new ArrayList<Text>();
	}
	public void setText(String text)
	{
		textList.clear();
		Text t;
		float lastPosition = 0;
		char lastChat = ' ';
		for(int i = 0;i<text.length();i++)
		{
			t = new Text(window);
			lastChat = text.charAt(i);
			t.text += lastChat;
			now_text += lastChat;
			t.position.x = lastPosition;
			lastPosition = window.app.textWidth(now_text) * stepChar;
			t.parent = this;
			textList.add(t);
			window.add(t);
		}
	}
	public void setSize(float size)
	{
		for(Text t : textList)
		{
			t.size = size;
		}
	}
	public void addAllTexts(Window window)
	{
		for(Text text : textList)
			window.add(text);
	}
	@Override
	public void deleteShape()
	{
		for(Shape shape : textList)
			shape.isDelete = true;
	}
}
