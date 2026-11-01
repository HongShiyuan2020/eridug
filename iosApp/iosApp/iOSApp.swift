import SwiftUI
import SharedLogic


@main
struct iOSApp: App {
    init () {
        KoinIosKt.doInitKoinIos()
        #if DEBUG
            AppLogger.shared.setup(isDebug: true)
        #else
            AppLogger.shared.setup(isDebug: false)
        #endif
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
