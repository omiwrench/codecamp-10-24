import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    @State private var movies: [ApiMovie] = []
    
    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            Button("First movie") {
                Task {
                    movies = try await Greeting().getPopularMovies().results
                }
            }
            
            Text(movies.first?.title ?? "Loading...")
            
            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
