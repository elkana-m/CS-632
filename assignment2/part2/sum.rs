// cargo run
fn sum(slice: &[i32]) -> i32 {
    slice.iter().sum()
}

fn main() {
    // Vec owns heap memory; freed automatically at scope end.
    let mut v = Vec::with_capacity(5);
    v.extend([1, 2, 3, 4, 5]);

    let s = sum(&v);              // borrow (read-only)
    println!("sum = {}", s);

    let v2 = v;                   // move ownership; `v` no longer usable
    println!("len = {}", v2.len());
}