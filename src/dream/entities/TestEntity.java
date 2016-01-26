package dream.entities;

import java.util.Random;

public class TestEntity extends Entity {

	public TestEntity() {
		super(20, 20);
		this.loadTexture("TEST.png");
		this.setPostion(new Random().nextInt(2000), new Random().nextInt(2000));
	}
	
}
