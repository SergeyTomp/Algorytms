package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class SimpleBinaryMaxHeap {

    private int[] h;
    private int size;

    private SimpleBinaryMaxHeap() {
    }

    private void shiftUp(int k) {
        if (k > 0 && this.h[k] > this.h[(k - 1) / 2]) {
            int tmp = this.h[k];
            this.h[k] = this.h[(k - 1) / 2];
            this.h[(k - 1) / 2] = tmp;
            shiftUp((k - 1) / 2);
        }
    }

    private void shiftDown(int k) {
        if (2 * k + 1 < this.size) {
            int l = 2 * k + 1;
            if (2 * k + 2 < this.size && this.h[2 * k + 2] > this.h[2 * k + 1]) {
                l = 2 * k + 2;
            }
            if (this.h[l] > this.h[k]) {
                int tmp = this.h[k];
                this.h[k] = this.h[l];
                this.h[l] = tmp;
                shiftDown(l);
            }
        }
    }

    private void add(int v) {
        this.h[this.size] = v;
        this.shiftUp(this.size);
        this.size++;
    }

    private int extract() {
        if (this.size == 0) {
            return 0;
        }
        int r = this.h[0];
        this.size--;
        this.h[0] = this.h[this.size];
        this.shiftDown(0);
        return r;
    }

    int get() {
        if (this.size == 0) {
            return 0;
        }
        return this.h[0];
    }

    private void run() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            int n = Integer.parseInt(br.readLine());
            this.h = new int[n];
            this.size = 0;
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                if (s.contains("Insert ")) {
                    this.add(Integer.parseInt(s.substring(7)));
                }else System.out.println(this.extract());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new SimpleBinaryMaxHeap().run();
    }
}
