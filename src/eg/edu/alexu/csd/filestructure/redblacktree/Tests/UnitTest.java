
package eg.edu.alexu.csd.filestructure.redblacktree.Tests;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import javax.management.RuntimeErrorException;

import eg.edu.alexu.csd.filestructure.redblacktree.Trees.INode;
import eg.edu.alexu.csd.filestructure.redblacktree.Trees.IRedBlackTree;
import eg.edu.alexu.csd.filestructure.redblacktree.Trees.ITreeMap;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;


public class UnitTest {
	private final boolean debug = false;

	/**
	 * test get a null root.
	 */
	@Test
	public void testRootNull() {

		IRedBlackTree<String, String> redBlackTree = (IRedBlackTree<String, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);
		INode<String, String> root = null;

		try {
			root = redBlackTree.getRoot();
			if (debug)
				System.out.println("TestRootNull: (case null)");
			boolean check = false;
			if (root == null)
				check = true;
			if (!check)
				Assert.assertEquals(true, root.isNull());
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("TestRootNull: (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of tree", e);
		}
	}

	/**
	 * Test get the root of the tree.
	 */
	@Test
	public void testGetRoot() {

		IRedBlackTree<String, String> redBlackTree = (IRedBlackTree<String, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);
		INode<String, String> root = null;

		try {
			redBlackTree.insert("Soso", "Toto");
			root = redBlackTree.getRoot();
			Assert.assertEquals("Soso", root.getKey());
			Assert.assertEquals("Toto", root.getValue());
		}catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of tree", e);
		}
	}

	/**
	 * check isEmpty true case.
	 */
	@Test
	public void testIsEmptyTrue() {

		IRedBlackTree<String, String> redBlackTree = (IRedBlackTree<String, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Assert.assertEquals(true, redBlackTree.isEmpty());
		}catch (Throwable e) {
			TestRunner.fail("Fail to test if tree is Empty", e);
		}
	}

	/**
	 * check isEmpty false case.
	 */
	@Test
	public void testIsEmptyFalse() {

		IRedBlackTree<String, String> redBlackTree = (IRedBlackTree<String, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			redBlackTree.insert("soso", "toto");
			Assert.assertEquals(false, redBlackTree.isEmpty());
		}catch (Throwable e) {
			TestRunner.fail("Fail to test if tree is Empty", e);
		}
	}

	/**
	 * Test clearing the tree.
	 */
	@Test
	public void testClearTree() {

		IRedBlackTree<String, String> redBlackTree = (IRedBlackTree<String, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			redBlackTree.insert("soso", "toto");
			redBlackTree.clear();
			redBlackTree.clear();
			redBlackTree.insert("soso", "toto");
			redBlackTree.insert("toto", "toto");
			redBlackTree.insert("fofo", "toto");
			redBlackTree.insert("koko", "toto");
			Assert.assertEquals(false, redBlackTree.isEmpty());
			redBlackTree.clear();
			Assert.assertEquals(true, redBlackTree.isEmpty());
		}catch (Throwable e) {
			TestRunner.fail("Fail to clear the tree", e);
		}
	}

	/**
	 * Test normal search in a tree.
	 */
	@Test
	public void testNormalSearch() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			ArrayList<Integer> keysToSearch = new ArrayList<>();
			for(int i = 0; i < 1000; i++) {
				int key = r.nextInt(10000);
				if (i % 50 == 0)
					keysToSearch.add(key);
				redBlackTree.insert(key, "toto" + key);
			}
			for (int i = 0; i < keysToSearch.size(); i++) {
				String ans = redBlackTree.search(keysToSearch.get(i));
				Assert.assertEquals("toto" + keysToSearch.get(i), ans);
			}
		}catch (Throwable e) {
			TestRunner.fail("Fail to search for a key in the tree", e);
		}
	}

	/**
	 * Test search in an empty tree.
	 */
	@Test
	public void testSearchEmpty() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Assert.assertNull(redBlackTree.search(123));
		}catch (Throwable e) {
			TestRunner.fail("Fail to search for a key in the tree", e);
		}
	}

	/**
	 * Test search an absent key in a tree.
	 */
	@Test
	public void testSearchAbsentKey() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			for (int i = 0; i < 10000; i++)
				redBlackTree.insert(i, "koko" + i);
			Assert.assertNull(redBlackTree.search(100000));
		}catch (Throwable e) {
			TestRunner.fail("Fail to search for a key in the tree", e);
		}
	}

	/**
	 * Test stress search.
	 */
	@Test(timeout = 10000)
	public void testStressSearch() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			ArrayList<Integer> keysToSearch = new ArrayList<>();
			for(int i = 0; i < 10000000; i++) {
				int key = r.nextInt(100000);
				if (i % 50 == 0)
					keysToSearch.add(key);
				redBlackTree.insert(key, "toto" + key);
			}
			for (int i = 0; i < keysToSearch.size(); i++) {
				String ans = redBlackTree.search(keysToSearch.get(i));
				Assert.assertEquals("toto" + keysToSearch.get(i), ans);
			}
		}catch (Throwable e) {
			TestRunner.fail("Fail to search for a key in the tree", e);
		}
	}

	/**
	 * Test search with null.
	 */
	@Test
	public void testSearchWithNull() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			for(int i = 0; i < 100; i++) {
				int key = r.nextInt(100000);
				redBlackTree.insert(key, "toto" + key);
			}
			redBlackTree.search(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		}catch (Throwable e) {
			TestRunner.fail("Fail to handle search with null parameter", e);
		}
	}

	/**
	 * Test normal contains in a tree.
	 */
	@Test
	public void testNormalContains() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			ArrayList<Integer> keysToSearch = new ArrayList<>();
			for(int i = 0; i < 1000; i++) {
				int key = r.nextInt(10000);
				if (i % 50 == 0)
					keysToSearch.add(key);
				redBlackTree.insert(key, "toto" + key);
			}
			for (int i = 0; i < keysToSearch.size(); i++) {
				boolean ans = redBlackTree.contains(keysToSearch.get(i));
				Assert.assertEquals(true, ans);
			}
		}catch (Throwable e) {
			TestRunner.fail("Fail contains a key in the tree", e);
		}
	}

	/**
	 * Test contains with null.
	 */
	@Test
	public void testContainsWithNull() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			for(int i = 0; i < 100; i++) {
				int key = r.nextInt(100000);
				redBlackTree.insert(key, "toto" + key);
			}
			redBlackTree.contains(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		}catch (Throwable e) {
			TestRunner.fail("Fail to handle contains with null parameter", e);
		}
	}

	/**
	 * Test contains in an empty tree.
	 */
	@Test
	public void testContainsEmpty() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Assert.assertEquals(false, redBlackTree.contains(123));
		}catch (Throwable e) {
			TestRunner.fail("Fail contains a key in the tree", e);
		}
	}

	/**
	 * Test contains an absent key in a tree.
	 */
	@Test
	public void testContainsAbsentKey() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			for (int i = 0; i < 10000; i++)
				redBlackTree.insert(i, "koko" + i);
			Assert.assertEquals(false, redBlackTree.contains(100000));
		}catch (Throwable e) {
			TestRunner.fail("Fail contains a key in the tree", e);
		}
	}

	/**
	 * Test stress contains.
	 */
	@Test(timeout = 10000)
	public void testStressContains() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			ArrayList<Integer> keysToSearch = new ArrayList<>();
			for(int i = 0; i < 10000000; i++) {
				int key = r.nextInt(100000);
				if (i % 50 == 0)
					keysToSearch.add(key);
				redBlackTree.insert(key, "toto" + key);
			}
			for (int i = 0; i < keysToSearch.size(); i++) {
				boolean ans = redBlackTree.contains(keysToSearch.get(i));
				Assert.assertEquals(true, ans);
			}
		}catch (Throwable e) {
			TestRunner.fail("Fail contains a key in the tree", e);
		}
	}

	/**
	 * Test insert with null key.
	 */
	@Test
	public void testInsertionWithNullKey() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			for(int i = 0; i < 100; i++) {
				int key = r.nextInt(100000);
				redBlackTree.insert(key, "toto" + key);
			}
			redBlackTree.insert(null, "soso");
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		}catch (Throwable e) {
			TestRunner.fail("Fail to handle search with null parameter", e);
		}
	}

	/**
	 * Test insert with null value.
	 */
	@Test
	public void testInsertionWithNullValue() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			for(int i = 0; i < 100; i++) {
				int key = r.nextInt(100000);
				redBlackTree.insert(key, "toto" + key);
			}
			redBlackTree.insert(123, null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		}catch (Throwable e) {
			TestRunner.fail("Fail to handle search with null parameter", e);
		}
	}

	/**
	 * Test insert normal with random data.
	 */
	@Test
	public void testNormalInsertionWithRandomData() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			for(int i = 0; i < 100; i++) {
				int key = r.nextInt(1000);
				redBlackTree.insert(key, "toto" + key);
				Assert.assertTrue(verifyProps(redBlackTree.getRoot()));
			}

		}catch (Throwable e) {
			TestRunner.fail("Fail to insert a key in the tree", e);
		}
	}

	/**
	 * Test insert normal.
	 */
	@Test
	public void testNormalInsertion() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			redBlackTree.insert(20, "soso");
			redBlackTree.insert(15, "soso");
			redBlackTree.insert(10, "soso");
			redBlackTree.insert(7, "soso");
			redBlackTree.insert(9, "soso");
			redBlackTree.insert(12, "soso");
			redBlackTree.insert(24, "soso");
			redBlackTree.insert(22, "soso");
			redBlackTree.insert(13, "soso");
			redBlackTree.insert(11, "soso");

			String expectedAns = "12B9R15R7B10B13B22BNBNBNB11RNBNB20R24RNBNBNBNBNBNB";
			String actualAns = levelOrder(redBlackTree.getRoot());
			Assert.assertEquals(expectedAns, actualAns);
		}catch (Throwable e) {
			TestRunner.fail("Fail to insert a key in the tree", e);
		}
	}

	/**
	 * Test update normal.
	 */
	@Test
	public void testUpdateValue() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			redBlackTree.insert(20, "soso" + 20);
			redBlackTree.insert(15, "soso" + 15);
			redBlackTree.insert(10, "soso" + 10);
			redBlackTree.insert(7, "soso" + 7);
			redBlackTree.insert(9, "soso" + 9);
			redBlackTree.insert(12, "soso" + 12);
			redBlackTree.insert(24, "soso" + 24);
			redBlackTree.insert(22, "soso" + 22);
			redBlackTree.insert(13, "soso" + 13);
			redBlackTree.insert(11, "soso" + 11);

			Assert.assertEquals("soso" + 13, redBlackTree.search(13));
			redBlackTree.insert(13, "koko" + 13);
			Assert.assertEquals("koko" + 13, redBlackTree.search(13));
		}catch (Throwable e) {
			TestRunner.fail("Fail to insert a key in the tree", e);
		}
	}

	/**
	 * Test delete with null parameter.
	 */
	@Test
	public void testDeleteWithNull() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			redBlackTree.delete(null);
			Assert.fail("Fail to handle deletion with null parameter");
		} catch (RuntimeErrorException ex) {

		} catch (Throwable e) {
			TestRunner.fail("Fail to handle deletion with null parameter", e);
		}
	}

	/**
	 * Test delete all elements from tree.
	 */
	@Test
	public void testDeleteAllElementsInTree() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			HashSet<Integer> list = new HashSet<>();
			for (int i = 0; i < 5; i++) {
				int key = r.nextInt(10000);
				list.add(key);
				redBlackTree.insert(key, "soso" + key);
			}

			for (Integer elem : list)
				Assert.assertTrue(redBlackTree.delete(elem));
			INode<Integer, String> node = redBlackTree.getRoot();
			if (!(node == null || node.isNull()))
				Assert.fail();
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle deletion", e);
		}
//		try {
//			//case 1
////			redBlackTree.insert(30, "soso");
////			redBlackTree.insert(20, "soso");
////			redBlackTree.insert(40, "soso");
////			redBlackTree.insert(35, "soso");
////			redBlackTree.insert(50, "soso");
////			Assert.assertTrue(redBlackTree.delete(20));
//			//case 1
//			redBlackTree.insert(30, "soso");
//			redBlackTree.insert(20, "soso");
//			redBlackTree.insert(40, "soso");
//			redBlackTree.insert(35, "soso");
//			boolean returnV = redBlackTree.delete(20);
//			Assert.assertTrue(returnV);
//			INode<Integer, String> node = redBlackTree.getRoot();
////			if (!(node == null || node.isNull()))
////				Assert.fail();
//
//		} catch (Throwable e) {
//			TestRunner.fail("Fail to handle deletion", e);
//		}
	}

	/**
	 * Test delete random elements from tree.
	 */
	@Test
	public void testDeleteRandomElementsInTree() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			HashSet<Integer> list = new HashSet<>();
			for (int i = 0; i < 100000; i++) {
				int key = r.nextInt(10000);
				if (r.nextInt(5) % 4 == 0)
					list.add(key);
				redBlackTree.insert(key, "soso" + key);
			}

			for (Integer elem : list)
				Assert.assertTrue(redBlackTree.delete(elem));
			INode<Integer, String> node = redBlackTree.getRoot();
			if ((node == null || node.isNull()))
				Assert.fail();
			Assert.assertTrue(verifyProps(node));
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle deletion", e);
		}
	}

	/**
	 * Test delete elements while inserting in tree.
	 */
	@Test
	public void testDeleteWhileInsertingInTree() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < 100000; i++) {
				int key = r.nextInt(10000);
				redBlackTree.insert(key, "soso" + key);
				if (r.nextInt(5) % 4 == 0 && list.size() > 0) {
					int index = r.nextInt(list.size());
					redBlackTree.delete(list.get(index));
					list.remove(index);
					list.add(key);
				}
			}

			Assert.assertTrue(verifyProps(redBlackTree.getRoot()));
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle deletion", e);
		}
	}

	/**
	 * Test stress delete.
	 */
	@Test(timeout = 10000)
	public void testStressDelete() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			HashSet<Integer> list = new HashSet<>();
			for (int i = 0; i < 10000000; i++) {
				int key = r.nextInt(10000);
				list.add(key);
				redBlackTree.insert(key, "soso" + key);
			}
			for (Integer elem : list)
				redBlackTree.delete(elem);
			INode<Integer, String> node = redBlackTree.getRoot();
			if (!(node == null || node.isNull()))
				Assert.fail();
			Assert.assertTrue(verifyProps(node));
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle deletion", e);
		}
	}

	/**
	 * Test delete absent elements.
	 */
	@Test
	public void testDeleteAbsentElementsInTree() {

		IRedBlackTree<Integer, String> redBlackTree = (IRedBlackTree<Integer, String>) TestRunner.getImplementationInstanceForInterface(IRedBlackTree.class);

		try {
			Random r = new Random();
			HashSet<Integer> list = new HashSet<>();
			for (int i = 0; i < 100000; i++) {
				int key = r.nextInt(10000);
				redBlackTree.insert(key, "soso" + key);
				list.add(key);
			}

			for (int i = 0; i < 100; i++) {
				int key = r.nextInt(10000);
				if (!list.contains(key))
					Assert.assertFalse(redBlackTree.delete(key));
			}

			Assert.assertTrue(verifyProps(redBlackTree.getRoot()));
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle deletion", e);
		}
	}

	/**
	 * Test ceiling Entry with null.
	 */
	@Test
	public void testCeilingEntryWithNull() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.ceilingEntry(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		} catch (Throwable e) {
			TestRunner.fail("Fail to handle ceiling with null parameter", e);
		}
	}

	/**
	 * Test ceiling Entry normal exact.
	 */
	@Test
	public void testCeilingEntry1() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(1000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.shuffle(list);
			int key = list.get(r.nextInt(list.size()));
			Map.Entry<Integer, String> entry = treemap.ceilingEntry(key);
			Assert.assertEquals(key, entry.getKey().intValue());
			Assert.assertEquals("soso" + key, entry.getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail in ceiling entry", e);
		}
	}

	/**
	 * Test ceiling Entry normal.
	 */
	@Test
	public void testCeilingEntry2() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.sort(list);
			int key = list.get((list.size() / 2)) - 2;
			for (int i = 0; i < list.size(); i++)
				if (key > list.get(i))
					continue;
				else {
					key = list.get(i);
					break;
				}
			Map.Entry<Integer, String> entry = treemap.ceilingEntry(key);
			Assert.assertEquals(key, entry.getKey().intValue());
			Assert.assertEquals("soso" + key, entry.getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail in ceiling entry", e);
		}
	}

	/**
	 * Test ceiling key with null.
	 */
	@Test
	public void testCeilingKeyWithNull() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.ceilingKey(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		} catch (Throwable e) {
			TestRunner.fail("Fail to handle ceiling with null parameter", e);
		}
	}

	/**
	 * Test ceiling key normal exact.
	 */
	@Test
	public void testCeilingKey1() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(1000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.shuffle(list);
			int key = list.get(r.nextInt(list.size()));
			Integer entry = treemap.ceilingKey(key);
			Assert.assertEquals(key, entry.intValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail in ceiling entry", e);
		}
	}

	/**
	 * Test ceiling Key normal.
	 */
	@Test
	public void testCeilingKey2() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.sort(list);
			int key = list.get((list.size() / 2)) - 2;
			for (int i = 0; i < list.size(); i++)
				if (key > list.get(i))
					continue;
				else {
					key = list.get(i);
					break;
				}
			Integer entry = treemap.ceilingKey(key);
			Assert.assertEquals(key, entry.intValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail in ceiling key", e);
		}
	}

	/**
	 * Test clear elements from treemap.
	 */
	@Test
	public void testClearElementsInTreeMap() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			treemap.clear();
			Assert.assertEquals(0, treemap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail in clearing elments from treemap", e);
		}
	}

	/**
	 * Test contains key with null parameter.
	 */
	@Test
	public void testContaninKeyWithNullparameter() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.containsKey(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		}
		catch (Throwable e) {
			TestRunner.fail("Fail in handle containsKey with null parameter", e);
		}
	}

	/**
	 * Test contains key normal.
	 */
	@Test
	public void testContaninKeyNormal() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(10000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.shuffle(list);
			int key = list.get(r.nextInt(list.size()));
			Assert.assertTrue(treemap.containsKey(key));
		} catch (Throwable e) {
			TestRunner.fail("Fail in containsKey", e);
		}
	}

	/**
	 * Test contains key normal.
	 */
	@Test
	public void testContaninKeyNotFound() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(10000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertFalse(treemap.containsKey(100001));
		} catch (Throwable e) {
			TestRunner.fail("Fail in containsKey", e);
		}
	}

	/**
	 * Test contains value with null parameter.
	 */
	@Test
	public void testContanisValueWithNullparameter() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.containsValue(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		}
		catch (Throwable e) {
			TestRunner.fail("Fail in containsValue with null parameter", e);
		}
	}

	/**
	 * Test contains key normal.
	 */
	@Test
	public void testContanisValueNormal() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(1000000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.shuffle(list);
			String key = "soso" + list.get(r.nextInt(list.size()));
			Assert.assertTrue(treemap.containsValue(key));
		} catch (Throwable e) {
			TestRunner.fail("Fail in containsValue", e);
		}
	}

	/**
	 * Test contains value not found.
	 */
	@Test
	public void testContanisValueNotFound() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(10000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertFalse(treemap.containsValue("koko" + 100001));
		} catch (Throwable e) {
			TestRunner.fail("Fail in containsValue", e);
		}
	}

	/**
	 * Test entrySet normal.
	 */
	@Test
	public void testEntrySet() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(10000);
				t.put(key, "soso" + key);
				treemap.put(key, "soso" + key);
			}
			Iterator<Entry<Integer, String>> itr1 = treemap.entrySet().iterator();
			Iterator<Entry<Integer, String>> itr2 = t.entrySet().iterator();

			while(itr1.hasNext() && itr2.hasNext()) {
				Entry<Integer, String> entry1 = itr1.next();
				Entry<Integer, String> entry2 = itr2.next();
				Assert.assertEquals(entry1, entry2);

			}
		} catch (Throwable e) {
			TestRunner.fail("Fail in entrySet", e);
		}
	}

	/**
	 * Test first entry.
	 */
	@Test
	public void testFirstEntry() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
			t.put(5, "soso" + 5);
			treemap.put(5, "soso" + 5);
			Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				t.put(key, "soso" + key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
		} catch (Throwable e) {
			TestRunner.fail("Fail in firstEntry", e);
		}
	}

	/**
	 * Test first key.
	 */
	@Test
	public void testFirstKey() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Assert.assertNull(treemap.firstKey());
			t.put(5, "soso" + 5);
			treemap.put(5, "soso" + 5);
			Assert.assertEquals(t.firstKey(), treemap.firstKey());
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				t.put(key, "soso" + key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertEquals(t.firstKey(), treemap.firstKey());
		} catch (Throwable e) {
			TestRunner.fail("Fail in firstKey", e);
		}
	}

	/**
	 * Test floor Entry with null.
	 */
	@Test
	public void testfloorEntryWithNull() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.floorEntry(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		} catch (Throwable e) {
			TestRunner.fail("Fail to handle floorEntry with null parameter", e);
		}
	}

	/**
	 * Test floor Entry normal exact.
	 */
	@Test
	public void testfloorEntry1() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(1000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.shuffle(list);
			int key = list.get(r.nextInt(list.size()));
			Map.Entry<Integer, String> entry = treemap.floorEntry(key);
			Assert.assertEquals(key, entry.getKey().intValue());
			Assert.assertEquals("soso" + key, entry.getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail in floorEntry", e);
		}
	}

	/**
	 * Test floor Entry normal.
	 */
	@Test
	public void testfloorEntry2() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.sort(list);
			int key = list.get((list.size() / 2)) - 2;
			for (int i = 0; i < list.size(); i++)
				if (list.get(i) > key)
					break;
				else {
					key = list.get(i);
				}
			Map.Entry<Integer, String> entry = treemap.floorEntry(key);
			Assert.assertEquals(key, entry.getKey().intValue());
			Assert.assertEquals("soso" + key, entry.getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail in floor entry", e);
		}
	}

	/**
	 * Test floor key with null.
	 */
	@Test
	public void testfloorKeyWithNull() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.floorKey(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {

		} catch (Throwable e) {
			TestRunner.fail("Fail to handle floorKey with null parameter", e);
		}
	}

	/**
	 * Test floor key normal exact.
	 */
	@Test
	public void testfloorKey1() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(1000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.shuffle(list);
			int key = list.get(r.nextInt(list.size()));
			Integer entry = treemap.floorKey(key);
			Assert.assertEquals(key, entry.intValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail in floorKey", e);
		}
	}

	/**
	 * Test floor key normal.
	 */
	@Test
	public void testfloorKey2() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			List<Integer> list = new ArrayList<>();
			Random r = new Random();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.sort(list);
			int key = list.get((list.size() / 2)) - 2;
			for (int i = 0; i < list.size(); i++)
				if (list.get(i) > key)
					break;
				else {
					key = list.get(i);
				}
			Integer entry = treemap.floorKey(key);
			Assert.assertEquals(key, entry.intValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail in floor key", e);
		}
	}

	/**
	 * Test get in treemap.
	 */
	@Test
	public void testGetElementInTreemap() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Random r = new Random();
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				String val = "" + r.nextInt(10000);
				list.add(key);
				t.put(key, val);
				treemap.put(key, val);
			}
			Collections.shuffle(list);
			int index = list.get(r.nextInt(list.size()));
			Assert.assertEquals(t.get(index), treemap.get(index));
		} catch (Throwable e) {
			TestRunner.fail("Fail in get element from treemap", e);
		}
	}

	/**
	 * Test get in treemap with null parameter.
	 */
	@Test
	public void testGetElementInTreemapWithNullParamerter() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.get(null);
			Assert.fail();
		} catch (RuntimeErrorException ex){
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle get with null parameter", e);
		}
	}

	private String levelOrder(INode<Integer, String> root) {
		StringBuilder sb = new StringBuilder();
		Queue<INode<Integer, String>> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				INode<Integer, String> cur = q.poll();
				if (cur != null && !cur.isNull()) {
					sb.append(cur.getKey() + (cur.getColor()? "R" : "B"));
					q.add(cur.getLeftChild());
					q.add(cur.getRightChild());
				}
				else {
					sb.append("NB");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Test get not found in treemap.
	 */
	@Test
	public void testGetElementInTreemapNotFound() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			Random r = new Random();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				String val = "" + r.nextInt(10000);
				treemap.put(key, val);
			}
			Assert.assertNull(treemap.get(1000001));
		} catch (Throwable e) {
			TestRunner.fail("Fail in get element from treemap", e);
		}
	}

	/**
	 * Test headMap with null parameter.
	 */
	@Test
	public void testHeadMapWithNullparameter() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.headMap(null);
		} catch (RuntimeErrorException ex) {
		} catch (Throwable e) {
			TestRunner.fail("Fail in handle headMap with null parameter", e);
		}
	}

	/**
	 * Test headMap.
	 */
	@Test
	public void testHeadMap() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Random r = new Random();
			ArrayList<Integer> keys = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				String val = "" + r.nextInt(10000);
				treemap.put(key, val);
				t.put(key, val);
				keys.add(key);
			}
			int toKey = keys.get(r.nextInt(keys.size()));
			ArrayList<Map.Entry<Integer, String>> ans = treemap.headMap(toKey);
			ArrayList<Map.Entry<Integer, String>> realAns = new ArrayList<>(t.headMap(toKey).entrySet());
			Collections.sort(realAns, new Comparator<Map.Entry<Integer, String>>() {
				@Override
				public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
					return o1.getKey() - o2.getKey();
				}
			});
			for (int i = 0; i < ans.size(); i++)
				Assert.assertEquals(ans.get(i), realAns.get(i));
		} catch (Throwable e) {
 			TestRunner.fail("Fail in headMap", e);
		}
	}

	/**
	 * Test headMap with null parameter.
	 */
	@Test
	public void testHeadMapInclusiveWithNullparameter() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.headMap(null, true);
		} catch (RuntimeErrorException ex) {
		} catch (Throwable e) {
			TestRunner.fail("Fail in handle headMap with null parameter", e);
		}
	}

	/**
	 * Test headMap.
	 */
	@Test
	public void testHeadMapInclusive() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Random r = new Random();
			ArrayList<Integer> keys = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				String val = "" + r.nextInt(10000);
				treemap.put(key, val);
				t.put(key, val);
				keys.add(key);
			}
			int toKey = keys.get(r.nextInt(keys.size()));
			ArrayList<Map.Entry<Integer, String>> ans = treemap.headMap(toKey, true);
			ArrayList<Map.Entry<Integer, String>> realAns = new ArrayList<>(t.headMap(toKey, true).entrySet());
			Collections.sort(realAns, new Comparator<Map.Entry<Integer, String>>() {
				@Override
				public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
					return o1.getKey() - o2.getKey();
				}
			});
			for (int i = 0; i < ans.size(); i++)
				Assert.assertEquals(ans.get(i), realAns.get(i));
		} catch (Throwable e) {
 			TestRunner.fail("Fail in headMap", e);
		}
	}


	/**
	 * Test Keyset.
	 */
	@Test
	public void testKeySet() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Random r = new Random();
			ArrayList<Integer> keys = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				int key = r.nextInt(1000000);
				String val = "" + r.nextInt(10000);
				treemap.put(key, val);
				t.put(key, val);
				keys.add(key);
			}
			Set<Integer> ans = treemap.keySet();
			ArrayList<Integer> realAns = new ArrayList<>(t.keySet());
			Collections.sort(realAns);

			int i = 0;
			for (Integer elem : ans) {
				Assert.assertEquals(elem, realAns.get(i++));
			}

		} catch (Throwable e) {
 			TestRunner.fail("Fail in Keyset", e);
		}
	}

	/**
	 * Test last entry.
	 */
	@Test
	public void testLastEntry() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
			t.put(5, "soso" + 5);
			treemap.put(5, "soso" + 5);
			Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				t.put(key, "soso" + key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
		} catch (Throwable e) {
			TestRunner.fail("Fail in lastEntry", e);
		}
	}

	/**
	 * Test first key.
	 */
	@Test
	public void testLastKey() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Assert.assertNull(treemap.lastKey());
			t.put(5, "soso" + 5);
			treemap.put(5, "soso" + 5);
			Assert.assertEquals(t.lastKey(), treemap.lastKey());
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				t.put(key, "soso" + key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertEquals(t.lastKey(), treemap.lastKey());
		} catch (Throwable e) {
			TestRunner.fail("Fail in lastKey", e);
		}
	}

	/**
	 * Test pollFirstEntry element.
	 */
	@Test
	public void testpollFirstEntry() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Assert.assertEquals(t.size(), treemap.size());
			Assert.assertNull(treemap.pollFirstEntry());
			t.put(5, "soso" + 5);
			treemap.put(5, "soso" + 5);
			Assert.assertEquals(t.pollFirstEntry(), treemap.pollFirstEntry());
			Assert.assertEquals(t.size(), treemap.size());
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				t.put(key, "soso" + key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertEquals(t.size(), treemap.size());
			Assert.assertEquals(t.pollFirstEntry(), treemap.pollFirstEntry());
			Assert.assertEquals(t.size(), treemap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail in pollFirstEntry", e);
		}
	}


	/**
	 * Test pollLastEntry element.
	 */
	@Test
	public void testpollLastEntry() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Assert.assertEquals(t.size(), treemap.size());
			Assert.assertNull(treemap.pollLastEntry());
			t.put(5, "soso" + 5);
			treemap.put(5, "soso" + 5);
			Assert.assertEquals(t.pollLastEntry(), treemap.pollLastEntry());
			Assert.assertEquals(t.size(), treemap.size());
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				t.put(key, "soso" + key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertEquals(t.size(), treemap.size());
			Assert.assertEquals(t.pollLastEntry(), treemap.pollLastEntry());
			Assert.assertEquals(t.size(), treemap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail in pollLastEntry", e);
		}
	}

	/**
	 * Test put with null key.
	 */
	@Test
	public void testputWithNullKey() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.put(null, "soso");
			Assert.fail();
		} catch (RuntimeErrorException ex) {
		} catch (Throwable e) {
			TestRunner.fail("Fail in handle put with null parameter", e);
		}
	}

	/**
	 * Test put with null value.
	 */
	@Test
	public void testputWithNullValue() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.put(123, null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {
		} catch (Throwable e) {
			TestRunner.fail("Fail in handle put with null parameter", e);
		}
	}

	/**
	 * Test putAll with null value.
	 */
	@Test
	public void testputAllWithNullValue() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.putAll(null);
			Assert.fail();
		} catch (RuntimeErrorException ex) {
		} catch (Throwable e) {
			TestRunner.fail("Fail in handle putAll with null parameter", e);
		}
	}

	/**
	 * Test putAll.
	 */
	@Test
	public void testputAll() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			Random r = new Random();
			HashMap<Integer, String> map = new HashMap<>();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(1000);
				map.put(key, "soso" + key);
			}
			treemap.putAll(map);
			Assert.assertEquals(map.size(), treemap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail in putAll", e);
		}
	}

	/**
	 * Test remove with null parameter.
	 */
	@Test
	public void testRemoveWithNullparameter() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			treemap.remove(null);
		} catch (RuntimeErrorException ex) {
		} catch (Throwable e) {
			TestRunner.fail("Fail in remove with null parameter", e);
		}
	}

	/**
	 * Test remove normal.
	 */
	@Test
	public void testRemoveNoraml() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			Random r = new Random();
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.shuffle(list);
			int keyToRem = list.get(r.nextInt(list.size()));
			Assert.assertTrue(treemap.remove(keyToRem));
		} catch (Throwable e) {
			TestRunner.fail("Fail in remove", e);
		}
	}

	/**
	 * Test remove not found.
	 */
	@Test
	public void testRemoveNotFound() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			Random r = new Random();
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Assert.assertFalse(treemap.remove(100001));
		} catch (Throwable e) {
			TestRunner.fail("Fail in remove element", e);
		}
	}

	/**
	 * Test get values.
	 */
	@Test
	public void testValues() {

		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);

		try {
			TreeMap<Integer, String> t = new TreeMap<>();
			Random r = new Random();
			for (int i = 0; i < 1000; i++) {
				int key = r.nextInt(100000);
				treemap.put(key, "soso" + key);
				t.put(key, "soso" + key);
			}
			Collection<String> ans = treemap.values();
			Collection<String> ansReal = t.values();
			Object[] ansArr = ans.toArray();
			Object[] ansRealArr = ansReal.toArray();
			Arrays.sort(ansArr);
			Arrays.sort(ansRealArr);
			for (int i = 0; i < Math.max(ansArr.length, ansRealArr.length); i++)
				Assert.assertEquals(ansArr[i], ansRealArr[i]);
		} catch (Throwable e) {
			TestRunner.fail("Fail in getting values", e);
		}
	}

	@Test
	public void testClassContainsMapReference() {
		ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
		Field[] fields = treemap.getClass().getDeclaredFields();

		Queue<Field> q = new LinkedList<>();

		q.addAll(Arrays.asList(fields));

		while(!q.isEmpty()) {
			Field cur = q.poll();

			System.out.println("field name " + cur.getType());
			if (cur.getType().isPrimitive()) continue;

			if (cur.getType().isAssignableFrom(TreeMap.class))
				Assert.fail();
			q.addAll(Arrays.asList(cur.getType().getDeclaredFields()));
		}

	}

	private boolean validateBST(INode<Integer, String> node, INode<Integer, String> leftRange, INode<Integer, String> rightRange) {
		if (node == null || node.isNull()) return true;

		if ((leftRange == null || node.getKey().compareTo(leftRange.getKey()) > 0) &&
				(rightRange == null || node.getKey().compareTo(rightRange.getKey()) < 0))
			return validateBST(node.getLeftChild(), leftRange, node) &&
					validateBST(node.getRightChild(), node, rightRange);
		return false;
	}

	private boolean verifyProperty2(INode<Integer, String> node) {
		return node.getColor() == INode.BLACK;
	}

	private boolean verifyProperty3(INode<Integer, String> node) {
		if (node == null || node.isNull()) return node.getColor() == INode.BLACK;

		return verifyProperty3(node.getLeftChild()) && verifyProperty3(node.getRightChild());
	}

	private boolean verifyProperty4(INode<Integer, String> node) {
		if (node == null || node.isNull()) return true;
		if (isRed(node)) {
			return !isRed(node.getParent()) && !isRed(node.getLeftChild()) && !isRed(node.getRightChild());
		}

		return verifyProperty4(node.getLeftChild()) && verifyProperty4(node.getRightChild());
	}

	private boolean verifyProperty5(INode<Integer, String> node) {
		boolean[] ans = new boolean[]{true};
		verifyProperty5Helper(node, ans);
		return ans[0];
	}

	private int verifyProperty5Helper(INode<Integer, String> node, boolean[] ans) {
		if (node == null) return 1;

		int leftCount = verifyProperty5Helper(node.getLeftChild(), ans);
		int rightCount = verifyProperty5Helper(node.getRightChild(), ans);

		ans[0] = ans[0] && (leftCount == rightCount);
		return leftCount + (!isRed(node)? 1 : 0);
	}

	private boolean verifyProps(INode<Integer, String> root) {
		return verifyProperty2(root) && verifyProperty3(root) && verifyProperty4(root) && verifyProperty5(root) && validateBST(root, null, null);
	}

	private boolean isRed(INode<Integer, String> node) {
		if (node == null || node.isNull()) return INode.BLACK;
		return node.getColor() == INode.RED;
	}
}