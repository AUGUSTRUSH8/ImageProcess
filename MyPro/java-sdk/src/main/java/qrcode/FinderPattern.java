package qrcode;

public class FinderPattern {
	    float estimatedModuleSize;
	    int count;
	    float x;
	    float y;
	    public FinderPattern() {
	    	x=0;
	        y=0;
	        count=0;
	        estimatedModuleSize=0;
	    }
	    public FinderPattern(float posX, float posY, float estimatedModuleSize, int count) {
	    	x=posX;
	        y=posY;
	        this.estimatedModuleSize = estimatedModuleSize;
	        this.count = count;
	    }
	    float getX() {
	    	return x;
	    }
	    float getY() {
	    	return y;
	    }
	    boolean aboutEquals(float moduleSize,float i,float j) {
	    	if (Math.abs(i - getY()) <= moduleSize && Math.abs(j - getX()) <= moduleSize) {
	    	      float moduleSizeDiff = Math.abs(moduleSize - estimatedModuleSize);
	    	      return moduleSizeDiff <= 1.0f || moduleSizeDiff <= estimatedModuleSize;
	    	    }
	    	    return false;
	    }
	    void combineEstimate(float i,float j,float newModuleSize) {
	    	int combinedCount = count + 1;
	        float combinedX = (count * getX() + j) / combinedCount;
	        float combinedY = (count * getY() + i) / combinedCount;
	        float combinedModuleSize = (count * estimatedModuleSize + newModuleSize) / combinedCount;

	        x = combinedX;
	        y = combinedY;
	        estimatedModuleSize = combinedModuleSize;
	        count = combinedCount;
	    }

	  

}