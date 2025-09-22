import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Main {
  static class Blob { byte[] buf = new byte[1_000_000]; } // ~1MB

  public static void main(String[] args) {
    List<Blob> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) list.add(new Blob());

    WeakReference<List<Blob>> weak = new WeakReference<>(list);
    list = null;

    System.gc();
    System.out.println("List cleared; weak.get() = " + weak.get());
  }
}