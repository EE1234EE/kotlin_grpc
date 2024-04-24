package grpc

import com.example.quarkus.grpc.Products
import com.example.quarkus.grpc.Products.ProductResponse
import com.example.quarkus.grpc.ProductsService
import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni

@GrpcService
class MutinyProductService : ProductsService {

    val productsMap = LinkedHashMap<String, Uni<Products>>()

    override fun getProduct(request: Products.ProductRequest): Uni<ProductResponse> {

        val resp = ProductResponse.newBuilder()
            .apply {
                id = request.productId
                name = "Shoe"
                priceCents = 1234
                orderable = true
            }.build()

        return Uni.createFrom().item {
           resp
        }
    }
}
