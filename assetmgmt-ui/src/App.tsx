import './App.css'
import { useAuth } from './app/authContext'

function App() {
  const { user } = useAuth();

  return (
    <div className="p-6">
        <pre>{JSON.stringify(user, null, 2)}</pre>
    </div>
  )
}

export default App
