package br.com.alura.panucci.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.panucci.R
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.theme.PanucciTheme
import br.com.alura.panucci.ui.uistate.ProductDetailsUiState
import coil.compose.AsyncImage

@Composable
fun ProductDetailsScreen(
    onNavigateToCheckout: () -> Unit = {},
    modifier: Modifier = Modifier,
    uistate: ProductDetailsUiState = ProductDetailsUiState.Failure,
) {
    when (uistate) {
        ProductDetailsUiState.Failure -> {

            Box(Modifier.fillMaxSize()) {
                Text(
                    text = "Falha ao carregar detalhes do produto",
                    Modifier.align(Alignment.Center)
                )
            }
        }

        ProductDetailsUiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }

        is ProductDetailsUiState.Success -> {
            val product = uistate.product
            Column(
                modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                product.image?.let {
                    AsyncImage(
                        model = product.image,
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        placeholder = painterResource(id = R.drawable.placeholder),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(product.name, fontSize = 24.sp)
                    Text(product.price.toPlainString(), fontSize = 18.sp)
                    Text(product.description)
                    Button(
                        onClick = { onNavigateToCheckout() },
                        Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(text = "Pedir")
                    }
                }
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductDetailsScreenSuccessPreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uistate = ProductDetailsUiState.Success(product = sampleProducts.random())
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductDetailsScreenFailurePreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uistate = ProductDetailsUiState.Failure
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductDetailsScreenLoadingPreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uistate = ProductDetailsUiState.Loading
            )
        }
    }
}