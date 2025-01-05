# Projeto multi-disciplinar

Este projeto é um sistema de gerenciamento de infrações de trânsito.
Ele possibilita que condutores, agentes fiscais e administradores o acessem
e modifiquem, via interface gráfica, informações necessárias da área
(situação de multas, quantidade de pontos, modificação de usuários, emissão de atos infratórios,
 etc).

# Uso

Antes de utilizar o sistema, averigue que
1. Você tem um servidor [MySQL]() instalado no sistema.
2. Você executou os scripts no diretório [`sql`](sql/README.md).

Na aba de Releases deste repositório, haverá um arquivo `.jar` executável. Se seu sistema estiver configurado para
executar esses arquivos, apenas abra o arquivo `.jar` no explorador de arquivos. Caso contrário, execute este comando:
```shell
$ java -jar transitoIFPR-X.Y.Z.jar
```
No diretório que contém o arquivo, trocando X.Y.Z pela versão.

# Build

Caso deseje formar o arquivo `.jar` em seu sistema, siga esses passos:
1. Instale a ferramenta [Apache Maven](https://maven.apache.org/install.html) em seu sistema.
2. Navegue até o diretório base deste repositório.
3. Execute `$ mvn package`.
4. Execute o arquivo agora presente no diretório `target` formado pela ferramenta.
   
***OBS:*** *O nome do arquivo pode ser uma variação entre essas opções:*
* `transitoIFPR-X.Y.Z-SNAPSHOT-shaded.jar`
* `transitoIFPR-X.Y.Z-SNAPSHOT.jar`

*Se o primeiro estiver presente, é o que deverá ser executado. Caso contrário, execute o de baixo.*