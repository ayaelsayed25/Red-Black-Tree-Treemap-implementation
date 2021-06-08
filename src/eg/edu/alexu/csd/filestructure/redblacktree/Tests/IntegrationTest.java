package eg.edu.alexu.csd.filestructure.redblacktree.Tests;

import eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.IRedBlackTree;
import eg.edu.alexu.csd.filestructure.redblacktree.Interfaces.ITreeMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IntegrationTest {

	
    private final Class<?> redBlackTreeInterfaceToTest = IRedBlackTree.class;
    private final Class<?> treeMapInterfaceToTest = ITreeMap.class;
	
    @Test
    public void testCreationRedBlackTree() {	
    	List<Class<?>> candidateClasses = ReflectionHelper.findClassesImplementing(redBlackTreeInterfaceToTest, redBlackTreeInterfaceToTest.getPackage());
    	Assert.assertNotNull("Failed to create instance using interface '" + redBlackTreeInterfaceToTest.getName() + "' !", candidateClasses);
    	Assert.assertEquals("You have more than one public implementation of the interface", 1, candidateClasses.size());
    }
    
    @Test
    public void testCreationTreeMap() {	
    	List<Class<?>> candidateClasses = ReflectionHelper.findClassesImplementing(treeMapInterfaceToTest, treeMapInterfaceToTest.getPackage());
    	Assert.assertNotNull("Failed to create instance using interface '" + treeMapInterfaceToTest.getName() + "' !", candidateClasses);
    	Assert.assertEquals("You have more than one public implementation of the interface", 1, candidateClasses.size());
    }

}
