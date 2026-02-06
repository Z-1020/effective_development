package colortree;

public class ExampleTree extends FullNode {

	public ExampleTree() {
		super(Color.RED);
		this.addChild(new FullNode(Color.BLUE, 
					new FullNode(Color.RED),
					new FullNode(Color.BLUE)));
		this.addChild(new FullNode(Color.RED, 
				new FullNode(Color.BLUE)));
		this.addChild(new FullNode(Color.BLUE, 
				new FullNode(Color.RED),
				new FullNode(Color.BLUE),
				new FullNode(Color.RED)));
	}
}
