<h1 align="center">Kepify</h1>
<p align="center">Consulte detalhes sobre endereços de forma prática e eficiente</p>

## Sobre :book:

Kepify é um aplicativo Android que permite consultar informações detalhadas sobre um CEP informado, incluindo estado, cidade, bairro, rua e imagens associadas ao local. O projeto foi desenvolvido com Kotlin e Jetpack Compose, aplicando conceitos essenciais do desenvolvimento Android moderno, como:

- **Composables**: Criação de interfaces declarativas reutilizáveis
- **Recomposição**: Atualização eficiente da UI conforme mudanças no estado
- **Corotinas**: Execução assíncrona para chamadas de rede e processos em segundo plano
- **ViewModel**: Gerenciamento de estado e ciclo de vida
- **Activity**: Estrutura base do app
- **MVVM (Model-View-ViewModel)**: Arquitetura para separação de responsabilidades

## Tecnologias :gear:

O projeto utiliza diversas tecnologias para oferecer uma experiência otimizada:

- **Kotlin**: Linguagem moderna e concisa para desenvolvimento Android
- **Jetpack Compose**: Framework para UI declarativa e reativa
- **Material3**: Design moderno e consistente com as diretrizes do Material Design 3
- **Koin**: Injeção de dependências simplificada
- **Retrofit**: Cliente HTTP para consumo de APIs REST
- **Ktor**: Cliente HTTP alternativo para chamadas de rede
- **Coil**: Carregamento e exibição eficiente de imagens
- **JUnit**: Framework para testes unitários
- **Mockk**: Biblioteca para mocks em testes

> :warning: **Atenção:** Em um projeto real, recomenda-se utilizar **ou Retrofit ou Ktor**, não ambos simultaneamente. Neste projeto de estudo, os dois foram incluídos para explorar a criação de uma interface que suporte ambas as bibliotecas, permitindo alternar entre elas sem modificar a implementação subjacente.

## Funcionalidades :sparkles:

- Busca de endereços por CEP
- Exibição de informações detalhadas do local
- Apresentação de imagens relacionadas ao CEP
- Interface intuitiva e responsiva
- Compartilhamento das informações

## Imagens :framed_picture:

<img src="https://github.com/user-attachments/assets/b77f96d8-6ea7-46b2-ae83-37b591b5da6d" alt="Tela inicial" width=270 />
<img src="https://github.com/user-attachments/assets/6a8ca07f-ccf2-4041-8320-d634a58aa4db" alt="Tela inicial depois de preencher o CEP (inicio)" width=270 />
<img src="https://github.com/user-attachments/assets/dfbab63d-dc8a-4da7-a46f-df4c900de46b" alt="Tela inicial depois de preencher o CEP (fim)" width=270 />

## Como executar :rocket:

### Pré-requisitos:

- Android Studio instalado (versão mais recente recomendada)
- Kotlin configurado
- Emulador ou dispositivo Android conectado
- Chave de API do Google Maps (para pesquisar imagens das ruas)

### Passos:

1. Clone este repositório:
   ```sh
   git clone https://github.com/SilvanoGPM/kepify.git
   ```
2. Abra o projeto no Android Studio
3. Crie na raiz do projeto o arquivo `secret.properties` e adicone a linha: `GOOGLE_API_KEY="SUA_API_KEY"`
4. Construa e execute o aplicativo no emulador ou dispositivo

## Contribuição :handshake:

Sinta-se à vontade para contribuir! Sugestões, melhorias e correções são bem-vindas.

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b minha-feature`)
3. Commit suas alterações (`git commit -m 'Adicionando nova feature'`)
4. Envie para o repositório (`git push origin minha-feature`)
5. Abra um Pull Request
