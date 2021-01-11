fun solution(products: List<String>, product: String) {
    for ((index, prod) in products.withIndex()){
        if(prod==product){
            print(index)
            print (" ")
        }
    }
}
